package caden.cadensmagicmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MoonoakBlock extends Block {
    public static final BooleanProperty MOON_VISIBLE = BooleanProperty.of("moon_visible");
    public static final BooleanProperty NATURAL = BooleanProperty.of("spawned");

    public MoonoakBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(MOON_VISIBLE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MOON_VISIBLE);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean moonVisible = isMoonVisible(world);
        world.setBlockState(pos, state.with(MOON_VISIBLE, moonVisible), 3);

        world.scheduleBlockTick(pos, this, 20);
    }

    private boolean isMoonVisible(World world) {
        if (world.isRaining() || world.isThundering() || world.getMoonPhase() == 4) {
            return false;
        }
        else if (world.getDimension().hasSkyLight()) {
            long time = world.getTimeOfDay() % 24000;
            System.out.println("Current time: " + time);
            boolean visible = time >= 13000 && time <= 23000;
            System.out.println("Is moon visible? " + visible);
            return visible;
        }
        return false;
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) { // Only schedule ticks on the server
            world.scheduleBlockTick(pos, this, 20);
        }
    }

}