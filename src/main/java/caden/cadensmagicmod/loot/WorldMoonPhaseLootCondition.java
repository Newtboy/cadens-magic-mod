package caden.cadensmagicmod.loot;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import java.util.List;

public record WorldMoonPhaseLootCondition(List<MoonPhase> acceptablePhases) implements LootCondition {
    static class LootConditions { // is contained in some class, name doesn't matter
        static LootCondition IS_CLEAR_AND_NIGHT =
                // and
                new AllOfLootCondition.Builder().and(
                                // if the weather is clear
                                () -> new WorldWeatherLootCondition(WorldWeatherLootCondition.WeatherType.CLEAR)
                        ).and(
                                // if any of theses are true
                                new AnyOfLootCondition.Builder().or(
                                        // if the time is from midnight to 6 am
                                        () -> new WorldTimeLootCondition(0, 1200)
                                ).or(
                                        // if the time is from 8 pm to 2400 hours
                                        () -> new WorldTimeLootCondition(18000, 24000)
                                )
                        )
                        .build();
    }
    public static final MapCodec<WorldMoonPhaseLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.list(MoonPhase.CODEC).fieldOf("acceptable_phases").forGetter(WorldMoonPhaseLootCondition::acceptablePhases)
            ).apply(instance, WorldMoonPhaseLootCondition::new)
    );
    public WorldMoonPhaseLootCondition(MoonPhase... acceptablePhases) {
        this(List.of(acceptablePhases));
    }

    @Override
    public LootConditionType getType() {
        return ModLootConditionTypes.MOON_PHASE;
    }

    public enum MoonPhase {
        FullMoon,
        WaningGibbous,
        ThirdQuarter,
        WaningCrescent,
        NewMoon,
        WaxingCrescent,
        FirstQuarter,
        WaxingGibbous;
        public static final Codec<MoonPhase> CODEC = Codec.STRING.xmap(
                MoonPhase::valueOf,
                MoonPhase::name
        );

        private static MoonPhase fromMoonPhaseIndex(int moonPhase) {
            return switch (moonPhase) {
                case 1 -> FullMoon;
                case 2 -> WaningGibbous;
                case 3 -> ThirdQuarter;
                case 4 -> WaningCrescent;
                case 5 -> NewMoon;
                case 6 -> WaxingCrescent;
                case 7 -> FirstQuarter;
                case 8 -> WaxingGibbous;
                default -> throw new IllegalArgumentException("Invalid Moon Phase");
            };
        }
    }

    @Override
    public boolean test(LootContext lootContext) {
        return acceptablePhases.contains(MoonPhase.fromMoonPhaseIndex(lootContext.getWorld().getMoonPhase()));
    }
}
