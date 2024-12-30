package caden.cadensmagicmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MoonOakBlock extends PillarBlock implements MoonCloakedBlock {
    public static final BooleanProperty NATURAL = BooleanProperty.of("spawned");

    public MoonOakBlock(Settings settings) {
        super(settings);
        setDefaultState(super.getDefaultState().with(NATURAL, true));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        world.updateNeighbors(pos, this);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NATURAL);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // Set NATURAL to false if placed by the player
        BlockState placementState = super.getPlacementState(ctx);
        return placementState != null ? placementState.with(NATURAL, false) : null;
    }

    @Override
    public BlockState getCloakedState(BlockState blockState) {
        return Blocks.OAK_LOG.getDefaultState()
                .with(AXIS, blockState.get(AXIS));
    }
}