package caden.cadensmagicmod.loot;

import caden.cadensmagicmod.block.custom.MoonOakLog;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;

public class NaturalCheckLootCondition implements LootCondition {

    private final boolean isNaturallyGenerated;

    public NaturalCheckLootCondition(boolean isNaturallyGenerated) {
        this.isNaturallyGenerated = isNaturallyGenerated;
    }

    @Override
    public LootContext apply(LootContext context) {
        // Logic to check if the block is naturally generated
        // You could check world generation metadata or block properties here.
        return isNaturallyGenerated ? LootContext.TRUE : LootContext.FALSE;
    }

    @Override
    public LootConditionType getType() {
        return NATURAL;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return false;
    }
}
