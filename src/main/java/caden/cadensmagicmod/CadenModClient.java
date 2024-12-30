package caden.cadensmagicmod;

import caden.cadensmagicmod.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

public class CadenModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOON_OAK_LEAVES, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((BlockState state, BlockRenderView world, BlockPos pos, int tintIndex) -> {
            if (world == null) return -1;
            if (world.getBiomeFabric(pos) == null) return -1;
            return world.getBiomeFabric(pos).value().getFoliageColor();
        }, ModBlocks.MOON_OAK_LEAVES);
    }
}
