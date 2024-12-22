package caden.cadensmagicmod.loot;

import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModLootConditionTypes {
    public static final LootConditionType WEATHER = new LootConditionType(WorldWeatherLootCondition.CODEC);
    public static final LootConditionType TIME = new LootConditionType(WorldTimeLootCondition.CODEC);
    public static final LootConditionType MOON_PHASE = new LootConditionType(WorldMoonPhaseLootCondition.CODEC);
    public static final LootConditionType NONE_OF = new LootConditionType(NoneOfLootCondition.CODEC);

    public static void register() {
        Registry.register(
                Registries.LOOT_CONDITION_TYPE,
                Identifier.of("cadens-magic-mod", "weather"),
                WEATHER
        );
        Registry.register(
                Registries.LOOT_CONDITION_TYPE,
                Identifier.of("cadens-magic-mod", "time"),
                TIME
        );
        Registry.register(
                Registries.LOOT_CONDITION_TYPE,
                Identifier.of("cadens-magic-mod", "moon_phase"),
                MOON_PHASE
        );
        Registry.register(
                Registries.LOOT_CONDITION_TYPE,
                Identifier.of("cadens-magic-mod", "none_of"),
                NONE_OF
        );
    }
}