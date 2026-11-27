package net._sofai.sat.gui;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net._sofai.sat.SofaiSStuffAndTweaksClient;
import net._sofai.sat.recipe.SewingTableRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;

import java.util.List;

public class SewingTableScreen extends AbstractContainerScreen<SewingTableMenu> {
    private static final Identifier SCROLLER_SPRITE = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "container/sewingtable/scroller");
    private static final Identifier SCROLLER_DISABLED_SPRITE = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "container/sewingtable/scroller_disabled");
    private static final Identifier RECIPE_SELECTED_SPRITE = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "container/sewingtable/recipe_selected");
    private static final Identifier RECIPE_HIGHLIGHTED_SPRITE = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "container/sewingtable/recipe_highlighted");
    private static final Identifier RECIPE_SPRITE = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "container/sewingtable/recipe");
    private static final Identifier BG_LOCATION = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "textures/gui/sprites/sewingtable.png");

    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;
    private List<RecipeHolder<SewingTableRecipe>> clientRecipes = List.of();
    private ItemStack lastInput = ItemStack.EMPTY;

    public SewingTableScreen(SewingTableMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        menu.registerUpdateListener(this::containerChanged);
        this.titleLabelY--;
    }

    private List<RecipeHolder<SewingTableRecipe>> getActiveRecipes() {
        ItemStack input = this.menu.getSlot(SewingTableMenu.INPUT_SLOT).getItem();
        if (!ItemStack.isSameItemSameComponents(input, this.lastInput)) {
            this.lastInput = input.copy();
            if (!input.isEmpty() && this.minecraft != null && this.minecraft.level != null) {
                SingleRecipeInput recipeInput = new SingleRecipeInput(input);
                this.clientRecipes = SofaiSStuffAndTweaksClient.syncedSewingRecipes
                        .stream()
                        .filter(holder -> holder.value().matches(recipeInput, this.minecraft.level))
                        .toList();
            } else {
                this.clientRecipes = List.of();
            }
        }
        return this.clientRecipes;
    }


    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        this.extractTooltip(graphics, mouseX, mouseY);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);

        int left = this.leftPos;
        int top = this.topPos;
        graphics.blit(RenderPipelines.GUI_TEXTURED, BG_LOCATION, left, top, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        int scrollPos = (int) (41.0F * this.scrollOffs);
        Identifier scroller = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, scroller, left + 119, top + 15 + scrollPos, 12, 15);

        int buttonsLeft = left + 52;
        int buttonsTop = top + 14;
        int endIndex = this.startIndex + 12;
        this.extractButtons(graphics, mouseX, mouseY, buttonsLeft, buttonsTop, endIndex);
        this.extractRecipes(graphics, buttonsLeft, buttonsTop, endIndex);
    }




    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        List<RecipeHolder<SewingTableRecipe>> recipes = this.getActiveRecipes();
        if (this.displayRecipes && !recipes.isEmpty()) {
            int buttonsLeft = this.leftPos + 52;
            int buttonsTop = this.topPos + 14;
            int endIndex = this.startIndex + 12;
            ItemStack input = this.menu.getSlot(SewingTableMenu.INPUT_SLOT).getItem();

            for (int i = this.startIndex; i < endIndex && i < recipes.size(); i++) {
                int offset = i - this.startIndex;
                int x = buttonsLeft + (offset % 4) * 16;
                int y = buttonsTop + (offset / 4) * 18 + 2;
                if (mouseX >= x && mouseX < x + 16 && mouseY >= y && mouseY < y + 18) {
                    ItemStack preview = recipes.get(i).value().assemble(new SingleRecipeInput(input));
                    graphics.setTooltipForNextFrame(this.font, preview, mouseX, mouseY);
                }
            }
        }
    }

    private void extractButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int buttonsLeft, int buttonsTop, int endIndex) {
        List<RecipeHolder<SewingTableRecipe>> recipes = this.getActiveRecipes();
        for (int i = this.startIndex; i < endIndex && i < recipes.size(); i++) {
            int offset = i - this.startIndex;
            int x = buttonsLeft + (offset % 4) * 16;
            int y = buttonsTop + (offset / 4) * 18 + 2;

            Identifier sprite;
            if (i == this.menu.getSelectedRecipeIndex()) {
                sprite = RECIPE_SELECTED_SPRITE;
            } else if (mouseX >= x && mouseY >= y && mouseX < x + 16 && mouseY < y + 18) {
                sprite = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                sprite = RECIPE_SPRITE;
            }
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, x, y - 1, 16, 18);
        }
    }

    private void extractRecipes(GuiGraphicsExtractor graphics, int buttonsLeft, int buttonsTop, int endIndex) {
        List<RecipeHolder<SewingTableRecipe>> recipes = this.getActiveRecipes();
        ItemStack input = this.menu.getSlot(SewingTableMenu.INPUT_SLOT).getItem();
        for (int i = this.startIndex; i < endIndex && i < recipes.size(); i++) {
            int offset = i - this.startIndex;
            int x = buttonsLeft + (offset % 4) * 16;
            int y = buttonsTop + (offset / 4) * 18 + 2;
            ItemStack preview = recipes.get(i).value().assemble(new SingleRecipeInput(input));
            graphics.item(preview, x, y);
            graphics.itemDecorations(this.font, preview, x, y);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        this.scrolling = false;
        List<RecipeHolder<SewingTableRecipe>> recipes = this.getActiveRecipes();
        if (this.displayRecipes && !recipes.isEmpty()) {
            int buttonsLeft = this.leftPos + 52;
            int buttonsTop = this.topPos + 14;
            int endIndex = this.startIndex + 12;

            for (int i = this.startIndex; i < endIndex && i < recipes.size(); i++) {
                int offset = i - this.startIndex;
                double dx = event.x() - (buttonsLeft + (offset % 4) * 16);
                double dy = event.y() - (buttonsTop + (offset / 4) * 18);
                if (dx >= 0.0 && dy >= 0.0 && dx < 16.0 && dy < 18.0 && this.menu.clickMenuButton(this.minecraft.player, i)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, i);
                    return true;
                }
            }

            int scrollLeft = this.leftPos + 119;
            int scrollTop = this.topPos + 9;
            if (event.x() >= scrollLeft && event.x() < scrollLeft + 12 && event.y() >= scrollTop && event.y() < scrollTop + 54) {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double dragX, double dragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int top = this.topPos + 14;
            int bottom = top + 54;
            this.scrollOffs = ((float) event.y() - top - 7.5F) / ((bottom - top) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int) (this.scrollOffs * this.getOffscreenRows() + 0.5) * 4;
            return true;
        }
        return super.mouseDragged(event, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (super.mouseScrolled(mouseX, mouseY, scrollX, scrollY)) {
            return true;
        }
        if (this.isScrollBarActive()) {
            int rows = this.getOffscreenRows();
            float step = (float) scrollY / rows;
            this.scrollOffs = Mth.clamp(this.scrollOffs - step, 0.0F, 1.0F);
            this.startIndex = (int) (this.scrollOffs * rows + 0.5) * 4;
        }
        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && this.getActiveRecipes().size() > 12;
    }

    protected int getOffscreenRows() {return (this.getActiveRecipes().size() + 4 - 1) / 4 - 3;
    }
    private void containerChanged() {
        this.displayRecipes = this.menu.getSlot(SewingTableMenu.INPUT_SLOT).hasItem() && !this.getActiveRecipes().isEmpty();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
}
