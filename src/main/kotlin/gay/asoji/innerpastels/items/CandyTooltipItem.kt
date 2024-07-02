package gay.asoji.innerpastels.items

import gay.asoji.innerpastels.misc.CandyTranslationString
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.alchemy.PotionContents
import java.util.*

/**
 * Overridden Item class with a modified Hover Tooltip to show Hunger translation string, what tied Mob Effect, and always eat string
 *
 * @property Item.Properties
 * @property CandyTranslationString
 */
class CandyTooltipItem(properties: Properties, val candyTranslationString: CandyTranslationString) : Item(properties) {
    /**
     * Overrides Item's `appendHoverText` to add more things in it, notably Hunger translation string, tied Mob Effect [if there is one], and Always Eat string
     */
    override fun appendHoverText(itemStack: ItemStack, tooltipContext: TooltipContext, list: MutableList<Component>, tooltipFlag: TooltipFlag) {
        list.add(Component.translatable(candyTranslationString.candyHungerString))

        val effects = LinkedList<MobEffectInstance>()

        if (itemStack.components.get(DataComponents.FOOD) != null) {
            for (effect in itemStack.components.get(DataComponents.FOOD)!!.effects) {
                effects.add(effect.effect)
            }
        }

        PotionContents.addPotionTooltip(effects, list::add, 1.0f, tooltipContext.tickRate() ?: 20.0f)
        list.add(Component.translatable("item.innerpastels.candies.hunger.alwayseat"))
    }
}
