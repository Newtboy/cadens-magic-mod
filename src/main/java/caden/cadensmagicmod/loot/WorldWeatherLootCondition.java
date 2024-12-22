package caden.cadensmagicmod.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;

import java.util.List;

public record WorldWeatherLootCondition(List<WeatherType> acceptableWeather) implements LootCondition {
    public WorldWeatherLootCondition(WeatherType... acceptableWeather) {
        this(List.of(acceptableWeather));
    }

    public static final MapCodec<WorldWeatherLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.list(WeatherType.CODEC).fieldOf("acceptable_weather").forGetter(WorldWeatherLootCondition::acceptableWeather)
            ).apply(instance, WorldWeatherLootCondition::new)
    );

    @Override
    public LootConditionType getType() {
        return ModLootConditionTypes.WEATHER;
    }

    @Override
    public boolean test(LootContext lootContext) {

        return acceptableWeather.contains(WeatherType.fromWorldWeather(
                lootContext.getWorld().isRaining(),
                lootContext.getWorld().isThundering()
        ));
    }

    public enum WeatherType {
        CLEAR,
        RAIN,
        THUNDER;
        public static final Codec<WeatherType> CODEC = Codec.STRING.xmap(
                WeatherType::valueOf,
                WeatherType::name
        );

        public static WeatherType fromWorldWeather(boolean raining, boolean thundering) {
            if (!raining) {
                return CLEAR;
            } else if (thundering) {
                return THUNDER;
            } else {
                return RAIN;
            }
        }
    }
}