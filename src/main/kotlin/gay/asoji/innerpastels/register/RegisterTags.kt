package gay.asoji.innerpastels.register

import gay.asoji.innerpastels.InnerPastels
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

/**
 * Quickly register tags-related stuff
 */
object RegisterTags {
    /**
     * Creates a block tag
     * @property name Associated block tag name
     */
    fun createBlockTag(name: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, name))
    }

    /**
     * Creates an item tag
     * @property name Associated item tag name
     */
    fun createItemTag(name: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, name))
    }
}