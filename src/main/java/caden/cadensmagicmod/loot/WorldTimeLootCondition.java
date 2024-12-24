package caden.cadensmagicmod.loot;

import caden.cadensmagicmod.CadensMagicMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;

public record WorldTimeLootCondition(long min, long max) implements LootCondition {
    public static final MapCodec<WorldTimeLootCondition> CODEC = RecordCodecBuilder.mapCodec(instace ->
            instace.group(
                    Codec.LONG.fieldOf("min").forGetter(WorldTimeLootCondition::min),
                    Codec.LONG.fieldOf("max").forGetter(WorldTimeLootCondition::max)
            ).apply(instace, WorldTimeLootCondition::new)
    );

    @Override
    public LootConditionType getType() {
        return ModLootConditionTypes.TIME;
    }

    @Override
    public boolean test(LootContext lootContext) {
        var time = normalize(lootContext.getWorld().getTimeOfDay());
        return time >= min
                && time <= max;
    }

    public static long normalize(long time) {
        final long fullDay = 24000;
        final long minecraftTimeOffset = 6000L;
        time += minecraftTimeOffset;
        time %= fullDay;
        return time;
    }
}