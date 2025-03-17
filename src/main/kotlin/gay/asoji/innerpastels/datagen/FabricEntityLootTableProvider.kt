/*
  * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package gay.asoji.innerpastels.datagen

import gay.asoji.innerpastels.mixins.EntityLootSubProviderAccessor
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider
import net.fabricmc.fabric.impl.datagen.loot.FabricLootTableProviderImpl
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.CachedOutput
import net.minecraft.data.loot.EntityLootSubProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer


// This is a direct port of https://github.com/FabricMC/fabric/pull/4459 from 1.21.4 to 1.21.1 since this wasn't backported at this time.
abstract class FabricEntityLootTableProvider(
    private val output: FabricDataOutput,
    private val registryLookupFuture: CompletableFuture<HolderLookup.Provider>
) : EntityLootSubProvider(
    FeatureFlags.REGISTRY.allFlags(), registryLookupFuture.join()
), FabricLootTableProvider {

    private val excludedFromStrictValidation: MutableSet<ResourceLocation> = HashSet()

    /**
     * Disable strict validation for the given entity type.
     */
    fun excludeFromStrictValidation(entityType: EntityType<*>?) {
        excludedFromStrictValidation.add(BuiltInRegistries.ENTITY_TYPE.getKey(entityType))
    }

    override fun generate(biConsumer: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        this.generate()

        for (tables in (this as EntityLootSubProviderAccessor).map.values) {
            // Register each of this particular entity type's loot tables
            for ((key, value) in tables) {
                biConsumer.accept(key, value)
            }
        }

        if (output.isStrictValidationEnabled) {
            val missing: MutableSet<ResourceLocation> = mutableSetOf()


            // Find any entity types from this mod that are missing their main loot table
            for (entityTypeId in BuiltInRegistries.ENTITY_TYPE.keySet()) {
                if (entityTypeId.getNamespace().equals(output.modId)) {
                    val entityType: EntityType<*> = BuiltInRegistries.ENTITY_TYPE.get(entityTypeId)

                    val mainLootTableKey = entityType.defaultLootTable
                    if (mainLootTableKey.location().getNamespace().equals(output.modId)) {
                        val tables =
                            (this as EntityLootSubProviderAccessor).map.get(entityType)

                        if (tables == null || !tables.containsKey(mainLootTableKey)) {
                            missing.add(entityTypeId)
                        }
                    }
                }
            }

            missing.removeAll(this.excludedFromStrictValidation)

            check(missing.isEmpty()) { "Missing loot table(s) for ${missing}" }
        }
    }

    override fun run(output: CachedOutput): CompletableFuture<*> {
        return FabricLootTableProviderImpl.run(output, this, LootContextParamSets.ENTITY, this.output, this.registryLookupFuture);
    }

    override fun getName(): String {
        return "Entity Loot Tables"
    }
}