package caden.cadensmagicmod.world.tree;

import caden.cadensmagicmod.CadensMagicMod;
import caden.cadensmagicmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator MOON_OAK = new SaplingGenerator(CadensMagicMod.MOD_ID + ":moon_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MOON_OAK_KEY), Optional.empty());
}
