package caden.cadensmagicmod;

import caden.cadensmagicmod.block.ModBlocks;
import caden.cadensmagicmod.component.ModDataComponentTypes;
import caden.cadensmagicmod.item.ModItemGroups;
import caden.cadensmagicmod.item.ModItems;
import caden.cadensmagicmod.loot.ModLootConditionTypes;
import caden.cadensmagicmod.util.HammerUsageEvent;
import caden.cadensmagicmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.FireBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CadensMagicMod implements ModInitializer {
	public static final String MOD_ID = "cadens-magic-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponentTypes.registerDataComponentTypes();

		ModLootConditionTypes.register();

		ModWorldGeneration.GenerateModWorldGen();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MOON_OAK_LOG,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MOON_OAK_PLANKS,5,5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MOON_OAK_LEAVES,30,60);
	}
}