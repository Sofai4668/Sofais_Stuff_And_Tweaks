package net._sofai.sat.stat;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ModStats {

    public static final Stat<?> SEWING_TABLE_USED_STAT = makeCustomStat("sewing_table_used");

    private static Stat<?> makeCustomStat(String key) {
        Identifier identifier = Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, key);
        Identifier newStat = Registry.register(BuiltInRegistries.CUSTOM_STAT, key, identifier);

        return Stats.CUSTOM.get(newStat, StatFormatter.DEFAULT);

    }

    public static void registerStats() {
        SofaiSStuffAndTweaks.LOGGER.info("REgisting stats for" + SofaiSStuffAndTweaks.MOD_ID);
    }
}
