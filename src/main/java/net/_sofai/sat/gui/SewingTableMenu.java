package net._sofai.sat.gui;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net._sofai.sat.block.ModBlocks;
import net._sofai.sat.recipe.ModRecipes;
import net._sofai.sat.recipe.SewingTableRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

import java.util.List;

public class SewingTableMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private static final TagKey<Item> SEWABLE_TAG = TagKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "sewable")
    );

    private final ContainerLevelAccess access;
    private final Level level;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();

    private List<RecipeHolder<SewingTableRecipe>> serverRecipes = List.of();
    private ItemStack lastInput = ItemStack.EMPTY;
    private long lastSoundTime;
    private Runnable slotUpdateListener = () -> {};

    public final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            SewingTableMenu.this.slotsChanged(this);
            SewingTableMenu.this.slotUpdateListener.run();
        }
    };
    private final ResultContainer resultContainer = new ResultContainer();

    public SewingTableMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public SewingTableMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenuTypes.SEWING_TABLE, containerId);
        this.access = access;
        this.level = inventory.player.level();

        this.addSlot(new Slot(this.container, 0, 20, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(SEWABLE_TAG);
            }
        });

        this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack carried) {
                carried.onCraftedBy(player, carried.getCount());
                SewingTableMenu.this.resultContainer.awardUsedRecipes(player, List.of(SewingTableMenu.this.getSlot(INPUT_SLOT).getItem()));
                ItemStack remaining = SewingTableMenu.this.getSlot(INPUT_SLOT).remove(1);
                if (!remaining.isEmpty()) {
                    SewingTableMenu.this.setupResultSlot(SewingTableMenu.this.selectedRecipeIndex.get());
                }
                access.execute((lvl, pos) -> {
                    long gameTime = lvl.getGameTime();
                    if (SewingTableMenu.this.lastSoundTime != gameTime) {
                        lvl.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        SewingTableMenu.this.lastSoundTime = gameTime;
                    }
                });
                super.onTake(player, carried);
            }
        });

        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    public void registerUpdateListener(Runnable listener) {
        this.slotUpdateListener = listener;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.SEWING_TABLE);
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (this.selectedRecipeIndex.get() == buttonId) return false;
        if (buttonId >= 0) {
            this.selectedRecipeIndex.set(buttonId);
            if (buttonId < this.serverRecipes.size()) {
                this.setupResultSlot(buttonId);
            }
            return true;
        }
        return false;
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack input = this.getSlot(INPUT_SLOT).getItem();
        if (ItemStack.isSameItemSameComponents(input, this.lastInput)) return;

        this.lastInput = input.copy();
        this.selectedRecipeIndex.set(-1);
        this.getSlot(RESULT_SLOT).set(ItemStack.EMPTY);

        if (!input.isEmpty() && this.level instanceof ServerLevel serverLevel) {
            this.serverRecipes = serverLevel.recipeAccess()
                    .getAllMatches(ModRecipes.SEWING_TYPE, new SingleRecipeInput(input), serverLevel)
                    .toList();
        } else {
            this.serverRecipes = List.of();
        }
    }

    private void setupResultSlot(int index) {
        if (index >= 0 && index < this.serverRecipes.size()) {
            RecipeHolder<SewingTableRecipe> recipe = this.serverRecipes.get(index);
            this.resultContainer.setRecipeUsed(recipe);
            this.getSlot(RESULT_SLOT).set(recipe.value().assemble(new SingleRecipeInput(this.container.getItem(0))));
        } else {
            this.getSlot(RESULT_SLOT).set(ItemStack.EMPTY);
            this.resultContainer.setRecipeUsed(null);
        }
        this.broadcastChanges();
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        return target.container != this.resultContainer && super.canTakeItemForPickAll(carried, target);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == RESULT_SLOT) {
                stack.getItem().onCraftedBy(stack, player);
                if (!this.moveItemStackTo(stack, 2, 38, true)) return ItemStack.EMPTY;
                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex == INPUT_SLOT) {
                if (!this.moveItemStackTo(stack, 2, 38, false)) return ItemStack.EMPTY;
            } else if (stack.is(SEWABLE_TAG)) {
                if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= 2 && slotIndex < 29) {
                if (!this.moveItemStackTo(stack, 29, 38, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= 29 && slotIndex < 38 && !this.moveItemStackTo(stack, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
            slot.setChanged();
            if (stack.getCount() == clicked.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
            this.broadcastChanges();
        }
        return clicked;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((lvl, pos) -> this.clearContainer(player, this.container));
    }
}
