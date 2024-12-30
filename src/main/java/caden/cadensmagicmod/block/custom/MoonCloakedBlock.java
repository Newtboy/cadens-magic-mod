package caden.cadensmagicmod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public interface MoonCloakedBlock {
    default boolean isMoonVisible(World world) {
        if (world.isRaining() || world.isThundering() || world.getMoonPhase() == 4) {
            return false;
        }
        else if (world.getDimension().hasSkyLight()) {
            long time = world.getTimeOfDay() % 24000;
            return time >= 13000 && time <= 23000;
        }
        return false;
    }

    BlockState getCloakedState(BlockState blockState);
}
