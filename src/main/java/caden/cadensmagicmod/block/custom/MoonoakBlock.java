package caden.cadensmagicmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MoonoakBlock extends Block {
    public static final BooleanProperty MOON_VISIBLE = BooleanProperty.of("moon_visible");
    public static final BooleanProperty NATURAL = BooleanProperty.of("spawned");
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;

    public MoonoakBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(MOON_VISIBLE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MOON_VISIBLE);
        builder.add(AXIS);
        builder.add(NATURAL);
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

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // Set NATURAL to false if placed by the player
        return this.getDefaultState().with(NATURAL, false);
    }
}