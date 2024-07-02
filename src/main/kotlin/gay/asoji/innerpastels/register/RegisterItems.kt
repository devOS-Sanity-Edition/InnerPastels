package gay.asoji.innerpastels.register

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

/**
 * Registers Items to be used with
 */
object RegisterItems {
    /**
     * Registers an item under the Item Registry, with a resource location of your mod's namespace and item ID, and your defined Item properties
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name The ID of the Block Item
     * @property item An Item to be used
     */
    fun registerItem(modID: String, name: String, item: Item): Item {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modID, name), item)
    }

    /**
     * Registers a regular item under the Item Registry, with a normal item property, with a resource location of your mod's namespace and item ID
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name The ID of the Block Item
     */
    fun registerRegularItem(modID: String, name: String): Item {
        return registerItem(modID, name, Item(Item.Properties()))
    }

    /**
     * Registers a BlockItem under the Item registry, with a resource location of your mod's namespace and item ID, followed by what block it's registered to, and your defined Block Item properties
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name The ID of the Block Item
     * @property block The Block to create the Block Item out of
     * @property properties The properties for the Block Item
     */
    fun registerBlockItem(modID: String, name: String, block: Block, properties: Item.Properties): BlockItem {
        return Registry.register(
            BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modID, name), BlockItem(block, properties)
        )
    }
}