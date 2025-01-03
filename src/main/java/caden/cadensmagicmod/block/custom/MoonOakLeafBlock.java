package caden.cadensmagicmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MoonOakLeafBlock extends Block {
    public static final BooleanProperty MOON_VISIBLE = BooleanProperty.of("moon_visible");
    public static final BooleanProperty NATURAL = BooleanProperty.of("spawned");

    public MoonOakLeafBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(MOON_VISIBLE, false).with(NATURAL, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MOON_VISIBLE);
        builder.add(NATURAL);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // Set NATURAL to false if placed by the player
        return this.getDefaultState().with(NATURAL, false);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean moonVisible = isMoonVisible(world);
        boolean isNatural = state.get(NATURAL);
        world.setBlockState(pos, state.with(MOON_VISIBLE, moonVisible).with(NATURAL, isNatural), 3);

        world.scheduleBlockTick(pos, this, 20);
    }

    private boolean isMoonVisible(World world) {
        if (world.isRaining() || world.isThundering() || world.getMoonPhase() == 4) {
            return false;
        }
        else if (world.getDimension().hasSkyLight()) {
            long time = world.getTimeOfDay() % 24000;
            return time >= 13000 && time <= 23000;
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