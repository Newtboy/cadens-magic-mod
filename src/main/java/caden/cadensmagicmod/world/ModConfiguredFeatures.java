package caden.cadensmagicmod.world;

import caden.cadensmagicmod.CadensMagicMod;
import caden.cadensmagicmod.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    // CF(# ores in vein), -> PF (how it get placed (veins/chunk/y level) -> WG/BM(what/where/how)
    public static final RegistryKey<ConfiguredFeature<?,?>> MOON_OAK_KEY = registerKey("moon_oak");

        public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
            register(context, MOON_OAK_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.MOON_OAK_LOG), // block to be placed as the log
                    new StraightTrunkPlacer(5,6,3),

                    BlockStateProvider.of(ModBlocks.MOON_OAK_LEAVES), // block for leaves
                    new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), 3), // How the leaves get placed

                    new TwoLayersFeatureSize(1,0,2)).build()); // so they don't overlap????
        }

        public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
            return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(CadensMagicMod.MOD_ID, name));
        }

        private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                       RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
            context.register(key, new ConfiguredFeature<>(feature, configuration));
        }
    }