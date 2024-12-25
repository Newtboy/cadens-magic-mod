package caden.cadensmagicmod.item;

import caden.cadensmagicmod.CadensMagicMod;
import caden.cadensmagicmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CadensMagicMod.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .displayName(Text.translatable("itemgroup.cadens-magic-mod.pink_garnet_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);

                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.CAULIFLOWER);

                        entries.add(ModItems.STARLIGHT_ASHES);

                        entries.add(ModItems.PINK_GARNET_SWORD);
                        entries.add(ModItems.PINK_GARNET_AXE);
                        entries.add(ModItems.PINK_GARNET_HOE);
                        entries.add(ModItems.PINK_GARNET_SHOVEL);
                        entries.add(ModItems.PINK_GARNET_PICKAXE);
                    }).build());

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CadensMagicMod.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .displayName(Text.translatable("itemgroup.cadens-magic-mod.pink_garnet_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);

                        entries.add(ModBlocks.PINK_GARNET_ORE);
                        entries.add(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);

                        entries.add(ModBlocks.MAGIC_BLOCK);

                        entries.add(ModBlocks.PINK_GARNET_STAIRS);
                        entries.add(ModBlocks.PINK_GARNET_SLAB);

                        entries.add(ModBlocks.PINK_GARNET_BUTTON);
                        entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);

                        entries.add(ModBlocks.PINK_GARNET_FENCE);
                        entries.add(ModBlocks.PINK_GARNET_FENCE_GATE);
                        entries.add(ModBlocks.PINK_GARNET_WALL);

                        entries.add(ModBlocks.PINK_GARNET_DOOR);
                        entries.add(ModBlocks.PINK_GARNET_TRAPDOOR);

                        entries.add(ModBlocks.PINK_GARNET_LAMP);

                    }).build());

    public static final ItemGroup MAGIC_MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CadensMagicMod.MOD_ID, "magic_mod_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.MOON_OAK_LOG))
                    .displayName(Text.translatable("itemgroup.cadens-magic-mod.magic_mod_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.MOON_OAK_LOG);
                        entries.add(ModBlocks.MOON_OAK_LEAVES);
                        entries.add(ModBlocks.MOON_OAK_PLANKS);
                        entries.add(ModBlocks.MOON_OAK_WOOD);
                        entries.add(ModBlocks.STRIPPED_MOON_OAK_LOG);
                        entries.add(ModBlocks.STRIPPED_MOON_OAK_WOOD);
                        entries.add(ModBlocks.MOON_OAK_SAPLING);
                    }).build());

    public static void registerItemGroups() {

        CadensMagicMod.LOGGER.info("Registerying item tab " + CadensMagicMod.MOD_ID);
    }

}
