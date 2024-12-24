package caden.cadensmagicmod.loot;

import com.mojang.serialization.MapCodec;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;

import java.util.List;
import java.util.Optional;

public class NoneOfLootCondition extends AlternativeLootCondition {
    public static final MapCodec<NoneOfLootCondition> CODEC = createCodec(NoneOfLootCondition::new);

    public NoneOfLootCondition(List<LootCondition> terms) {
        super(terms, context -> {
            boolean result = terms.stream().noneMatch(term -> term.test(context));
            return result;
        });

        LootCondition IS_CLEAR_AND_NIGHT =
                new AllOfLootCondition.Builder()
                        .and(() -> new WorldWeatherLootCondition(WorldWeatherLootCondition.WeatherType.CLEAR))
                        .and(() -> new TimeCheckLootCondition(Optional.of(24000L), BoundedIntUnaryOperator.create(13000, 23000))) // Nighttime in ticks // Nighttime in ticks
                        .build();
        System.out.println("IS_CLEAR_AND_NIGHT evaluates to: " + IS_CLEAR_AND_NIGHT);

    }

    public NoneOfLootCondition(LootCondition... terms) {
        this(List.of(terms));
    }

    @Override
    public LootConditionType getType() {
        return ModLootConditionTypes.NONE_OF;
    }
}