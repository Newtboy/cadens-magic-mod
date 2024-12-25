package caden.cadensmagicmod.datagen;

import caden.cadensmagicmod.block.ModBlocks;
import caden.cadensmagicmod.item.ModItems;
import caden.cadensmagicmod.loot.NoneOfLootCondition;
import caden.cadensmagicmod.loot.WorldWeatherLootCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TimeCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static caden.cadensmagicmod.block.ModBlocks.*;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    static LootCondition IS_CLEAR_AND_NIGHT =
            new AllOfLootCondition.Builder()
                    .and(() -> new WorldWeatherLootCondition(WorldWeatherLootCondition.WeatherType.CLEAR))
                    .and(() -> new TimeCheckLootCondition(Optional.of(24000L), BoundedIntUnaryOperator.create(13000, 23000))) // Nighttime in ticks
                    .build();

    @Override
    public void generate() {
        addDrop(ModBlocks.PINK_GARNET_BLOCK); // for it to drop itself
        addDrop(ModBlocks.RAW_PINK_GARNET_BLOCK);
        addDrop(ModBlocks.MAGIC_BLOCK);

        addDrop(ModBlocks.PINK_GARNET_ORE, oreDrops(ModBlocks.PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET));

        // random drop number
        addDrop(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, multipleOreDrops(ModBlocks.DEEPSLATE_PINK_GARNET_ORE, ModItems.RAW_PINK_GARNET, 3,7));

        addDrop(ModBlocks.PINK_GARNET_STAIRS);
        addDrop(ModBlocks.PINK_GARNET_SLAB, slabDrops(ModBlocks.PINK_GARNET_SLAB));

        addDrop(ModBlocks.PINK_GARNET_BUTTON);
        addDrop(ModBlocks.PINK_GARNET_PRESSURE_PLATE);

        addDrop(ModBlocks.PINK_GARNET_WALL);
        addDrop(ModBlocks.PINK_GARNET_FENCE);
        addDrop(ModBlocks.PINK_GARNET_FENCE_GATE);

        addDrop(ModBlocks.PINK_GARNET_DOOR, doorDrops(ModBlocks.PINK_GARNET_DOOR));
        addDrop(ModBlocks.PINK_GARNET_TRAPDOOR);

        addDrop(MOON_OAK_LOG, LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(IS_CLEAR_AND_NIGHT) // Use the pre-defined condition
                                .with(ItemEntry.builder(MOON_OAK_LOG)) // Drops MOON_OAK_LOG when the condition is met
                ).pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(new NoneOfLootCondition(IS_CLEAR_AND_NIGHT)) // Use NoneOfLootCondition directly
                                .with(ItemEntry.builder(Items.OAK_LOG)) // Drops OAK_LOG when the condition is NOT met
                ));
        addDrop(MOON_OAK_LEAVES, LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(IS_CLEAR_AND_NIGHT)
                                .with(ItemEntry.builder(MOON_OAK_LEAVES))
                ).pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(new NoneOfLootCondition(IS_CLEAR_AND_NIGHT))
                                .with(ItemEntry.builder(Items.OAK_LEAVES))
                ));
        addDrop(MOON_OAK_PLANKS);
        //addDrop(MOON_OAK_SAPLING);
        addDrop(MOON_OAK_WOOD);
        addDrop(STRIPPED_MOON_OAK_WOOD);
        addDrop(STRIPPED_MOON_OAK_LOG);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
