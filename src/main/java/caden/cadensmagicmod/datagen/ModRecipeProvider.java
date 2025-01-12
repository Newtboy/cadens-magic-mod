package caden.cadensmagicmod.datagen;

import caden.cadensmagicmod.CadensMagicMod;
import caden.cadensmagicmod.CadensMagicModDataGenerator;
import caden.cadensmagicmod.block.ModBlocks;
import caden.cadensmagicmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE,
                ModBlocks.DEEPSLATE_PINK_GARNET_ORE);

        offerSmelting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");
        offerBlasting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 100, "pink_garnet");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_PINK_GARNET)
                .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET)) // Give recipe upon getting raw pink garnet
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
                .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
                .input(ModBlocks.MAGIC_BLOCK)
                .criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(exporter, Identifier.of(CadensMagicMod.MOD_ID, "raw_pink_garnet_from_magic_block")); // custom tag to have different name

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MOON_OAK_FENCE, 6)// idfk man figure it out later
                .pattern("   ")
                .pattern("OSO")
                .pattern("   ")
                .input('S', ModItems.MOON_OAK_STICK)
                .input('O', ModBlocks.MOON_OAK_PLANKS)
                .criterion(hasItem(ModItems.MOON_OAK_STICK), conditionsFromItem(ModItems.MOON_OAK_STICK)) // Recipe given when
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MOON_OAK_FENCE_GATE, 3)// idfk man figure it out later
                .pattern("   ")
                .pattern("SOS")
                .pattern("   ")
                .input('S', Items.STICK)
                .input('O', ModBlocks.MOON_OAK_PLANKS)
                .criterion(hasItem(ModItems.MOON_OAK_STICK), conditionsFromItem(ModItems.MOON_OAK_STICK)) // Recipe given when
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MOON_OAK_STAIRS, 6)// idfk man figure it out later
                .pattern("O  ")
                .pattern("OO ")
                .pattern("OOO")
                .input('O', ModBlocks.MOON_OAK_PLANKS)
                .criterion(hasItem(ModBlocks.MOON_OAK_PLANKS), conditionsFromItem(ModBlocks.MOON_OAK_PLANKS)) // Recipe given when
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MOON_OAK_BUTTON, 9)
                .input(ModBlocks.MOON_OAK_PLANKS)
                .criterion(hasItem(ModBlocks.MOON_OAK_PLANKS), conditionsFromItem(ModBlocks.MOON_OAK_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MOON_OAK_STICK, 4)// idfk man figure it out later
                .pattern("O")
                .pattern("O")
                .input('O', ModBlocks.MOON_OAK_PLANKS)
                .criterion(hasItem(ModBlocks.MOON_OAK_PLANKS), conditionsFromItem(ModBlocks.MOON_OAK_PLANKS)) // Recipe given when
                .offerTo(exporter);
    }
}
