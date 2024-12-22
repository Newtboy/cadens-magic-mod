package caden.cadensmagicmod.loot;

import com.mojang.serialization.MapCodec;
import net.minecraft.loot.condition.AlternativeLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;

import java.util.List;

public class NoneOfLootCondition extends AlternativeLootCondition {
    public static final MapCodec<NoneOfLootCondition> CODEC = createCodec(NoneOfLootCondition::new);

    public NoneOfLootCondition(List<LootCondition> terms) {
        super(terms, context -> terms.stream().noneMatch(term -> term.test(context)));
    }

    public NoneOfLootCondition(LootCondition... terms) {
        this(List.of(terms));
    }

    @Override
    public LootConditionType getType() {
        return ModLootConditionTypes.NONE_OF;
    }
}