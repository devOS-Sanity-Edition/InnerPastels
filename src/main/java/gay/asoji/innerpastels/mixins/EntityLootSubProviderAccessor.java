package gay.asoji.innerpastels.mixins;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(EntityLootSubProvider.class)
public interface EntityLootSubProviderAccessor {
    @Accessor
    Map<EntityType<?>, Map<ResourceKey<LootTable>, LootTable.Builder>> getMap();
}
