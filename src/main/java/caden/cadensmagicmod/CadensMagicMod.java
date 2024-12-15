package caden.cadensmagicmod;

import caden.cadensmagicmod.block.ModBlocks;
import caden.cadensmagicmod.item.ModItemGroups;
import caden.cadensmagicmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

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

	}
}