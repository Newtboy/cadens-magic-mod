package caden.cadensmagicmod.datagen;

import caden.cadensmagicmod.block.ModBlocks;
import caden.cadensmagicmod.block.custom.MoonOakLeafBlock;
import caden.cadensmagicmod.block.custom.MoonOakLog;
import caden.cadensmagicmod.block.custom.PinkGarnetLampBlock;
import caden.cadensmagicmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //make a variable that allows for the fences, stairs, etc. to use this block's texture
        BlockStateModelGenerator.BlockTexturePool pinkGarnetPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PINK_GARNET_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_PINK_GARNET_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_GARNET_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK); // replace some stuff with custom wood later

        pinkGarnetPool.stairs(ModBlocks.PINK_GARNET_STAIRS);
        pinkGarnetPool.slab(ModBlocks.PINK_GARNET_SLAB);

        pinkGarnetPool.button(ModBlocks.PINK_GARNET_BUTTON);
        pinkGarnetPool.pressurePlate(ModBlocks.PINK_GARNET_PRESSURE_PLATE);

        pinkGarnetPool.fence(ModBlocks.PINK_GARNET_FENCE);
        pinkGarnetPool.fenceGate(ModBlocks.PINK_GARNET_FENCE_GATE);
        pinkGarnetPool.wall(ModBlocks.PINK_GARNET_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.PINK_GARNET_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.PINK_GARNET_TRAPDOOR);

        final Identifier moonOffHorizontalIdentifier = Models.CUBE_COLUMN_HORIZONTAL.upload(
                ModBlocks.MOON_OAK_LOG,
                TextureMap.sideAndEndForTop(ModBlocks.MOON_OAK_LOG),
                blockStateModelGenerator.modelCollector);

        final Identifier moonOnVerticalIdentifier = Models.CUBE_COLUMN.upload(
                ModBlocks.MOON_OAK_LOG,
                "_on",
                new TextureMap()
                        .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.MOON_OAK_LOG, "_on"))
                        .put(TextureKey.END, TextureMap.getSubId(ModBlocks.MOON_OAK_LOG, "_top_on"))
                        .put(TextureKey.PARTICLE, TextureMap.getSubId(ModBlocks.MOON_OAK_LOG, "_on")),
                blockStateModelGenerator.modelCollector);

        final Identifier moonOnHorizontalIdentifier = Models.CUBE_COLUMN_HORIZONTAL.upload(
                ModBlocks.MOON_OAK_LOG,
                "_on",
                new TextureMap()
                        .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.MOON_OAK_LOG, "_on"))
                        .put(TextureKey.END, TextureMap.getSubId(ModBlocks.MOON_OAK_LOG, "_top_on"))
                        .put(TextureKey.PARTICLE, TextureMap.getSubId(ModBlocks.MOON_OAK_LOG, "_on")),
                blockStateModelGenerator.modelCollector);

        final Identifier moonOffVerticalIdentifier = Models.CUBE_COLUMN.upload(
                Blocks.OAK_LOG,
                TextureMap.sideAndEndForTop(Blocks.OAK_LOG),
                blockStateModelGenerator.modelCollector);

        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(ModBlocks.MOON_OAK_LOG)
                        .coordinate(BlockStateVariantMap.TripleProperty.create(
                                        MoonOakLog.AXIS,
                                        MoonOakLog.MOON_VISIBLE,
                                        MoonOakLog.NATURAL)
                                .register((axis, moonVisible, natural) -> {
                                    // Always use moonOn models if NATURAL is false
                                    boolean useMoonOn = !natural || moonVisible;

                                    Identifier horizontalIdentifier = useMoonOn ? moonOnHorizontalIdentifier : moonOffHorizontalIdentifier;
                                    Identifier verticalIdentifier = useMoonOn ? moonOnVerticalIdentifier : moonOffVerticalIdentifier;

                                    return switch (axis) {
                                        case Y -> BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, verticalIdentifier);
                                        case Z -> BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, horizontalIdentifier)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R90);
                                        case X -> BlockStateVariant.create()
                                                .put(VariantSettings.MODEL, horizontalIdentifier)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R90);
                                    };
                                })));

        Identifier moonLeafOffIdentifier = TexturedModel.CUBE_ALL.upload(Blocks.OAK_LEAVES, blockStateModelGenerator.modelCollector);
        Identifier moonLeafOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.MOON_OAK_LEAVES, "_on", Models.CUBE_ALL, TextureMap::all);

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.MOON_OAK_LEAVES)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(MoonOakLeafBlock.MOON_VISIBLE, moonLeafOnIdentifier, moonLeafOffIdentifier)));

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MOON_OAK_PLANKS);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.MOON_OAK_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);

        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);

//        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET_HAMMER, Models.HANDHELD);

        itemModelGenerator.register(ModItems.STARLIGHT_ASHES, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINK_GARNET_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_HOE, Models.HANDHELD);
    }
}
