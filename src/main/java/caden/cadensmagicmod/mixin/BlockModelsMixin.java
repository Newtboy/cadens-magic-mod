package caden.cadensmagicmod.mixin;

import caden.cadensmagicmod.block.custom.MoonCloakedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(BlockModels.class)
public class BlockModelsMixin {
    @Shadow private Map<BlockState, BakedModel> models;

    @Shadow @Final private BakedModelManager modelManager;

    @Inject(at = @At("HEAD"), method = "getModel", cancellable = true)
    private void getModel(BlockState state, CallbackInfoReturnable<BakedModel> cir) {
        if (state.getBlock() instanceof MoonCloakedBlock moonCloakedBlock) {
            World world = MinecraftClient.getInstance().world;
            if (world == null) return;
            if (!moonCloakedBlock.isMoonVisible(world)) {
                BakedModel overriddenModel = this.models.getOrDefault(moonCloakedBlock.getCloakedState(state), modelManager.getMissingModel());

                cir.setReturnValue(overriddenModel);
            }
        }
    }
}
