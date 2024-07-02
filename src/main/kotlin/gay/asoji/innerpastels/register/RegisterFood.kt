package gay.asoji.innerpastels.register

import gay.asoji.innerpastels.items.CandyTooltipItem
import gay.asoji.innerpastels.misc.CandyTranslationString
import gay.asoji.innerpastels.misc.secondsToTicks
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items

/**
 * Registers Food Items to be eaten with
 */
object RegisterFood {
    /**
     * Registers a candy food item
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_taffy`]
     * @property candyTranslationString Type of candy for hunger translation string
     * @property nutrition Candy's nutrition value
     * @property saturation Candy's saturation value
     * @property effect [MobEffect] tied to eating the candy
     * @property amplifier Potency of said [MobEffect] after eating it
     * @property seconds Amount of time the effect lasts
     */
    fun registerCandy(
        modID: String,
        name: String,
        candyTranslationString: CandyTranslationString,
        nutrition: Int,
        saturation: Int,
        effect: Holder<MobEffect>,
        amplifier: Int,
        seconds: Int
    ): CandyTooltipItem {
        return Items.registerItem(
            ResourceLocation.fromNamespaceAndPath(modID, name), CandyTooltipItem(
                Item.Properties().food(
                    FoodProperties.Builder()
                        .nutrition(nutrition)
                        .saturationModifier(saturation.toFloat())
                        .fast()
                        .alwaysEdible()
                        .effect(MobEffectInstance(effect, seconds.secondsToTicks(), amplifier), 1.0f).build()
                ),
                candyTranslationString
            )
        ) as CandyTooltipItem
    }

    /**
     * Registers a candy food item
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_taffy`]
     * @property candyTranslationString Type of candy for hunger translation string
     * @property nutrition Candy's nutrition value
     * @property saturation Candy's saturation value
     *
     * @return [CandyTooltipItem]
     */
    fun registerCandy(modID: String, name: String, candyTranslationString: CandyTranslationString, nutrition: Int, saturation: Int): CandyTooltipItem {
        return Items.registerItem(
            ResourceLocation.fromNamespaceAndPath(modID, name), CandyTooltipItem(
                Item.Properties().food(
                    FoodProperties.Builder()
                        .nutrition(nutrition)
                        .saturationModifier(saturation.toFloat())
                        .fast()
                        .alwaysEdible()
                        .build()
                ),
                candyTranslationString
            )
        ) as CandyTooltipItem
    }

    /**
     * Registers the Taffy candy item with a tied [MobEffect] for [Int] seconds
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_taffy`]
     * @property effect The corresponding [MobEffect] obtained when the candy is eaten
     * @property seconds Amount of seconds applied to the tied [effect]
     *
     * @return Taffy [registerCandy] with a defined Mod ID, Item ID, Mob Effect when eaten for a certain amount of seconds
     */
    fun registerTaffy(modID: String, name: String, effect: Holder<MobEffect>, seconds: Int): CandyTooltipItem {
        return registerCandy(modID, name, CandyTranslationString.TAFFY, 2, 2, effect, 0, seconds)
    }

    /**
     * Registers the Taffy candy item without any effects
     * 
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_taffy`]
     *
     * @return Taffy [registerCandy] with a defined Mod ID and Item ID, no [MobEffect]
     */
    fun registerTaffy(modID: String, name: String): CandyTooltipItem {
        return registerCandy(modID, name, CandyTranslationString.TAFFY, 2, 2)
    }

    /**
     * Registers the Cotton Candy item with a tied [MobEffect] for [Int] seconds
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_cotton_candy`]
     * @property effect The corresponding [MobEffect] obtained when the candy is eaten
     * @property seconds Amount of seconds applied to the tied [effect]
     *
     * @return Cotton Candy [registerCandy] with a defined Mod ID, Item ID, Mob Effect when eaten for a certain amount of seconds
     */
    fun registerCottonCandy(modID: String, name: String, effect: Holder<MobEffect>, seconds: Int): CandyTooltipItem {
        return registerCandy(modID, name, CandyTranslationString.COTTON, 3, 2, effect, 1, seconds)
    }

    /**
     * Registers the Cotton Candy item without any effects
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_cotton_candy`]
     *
     * @return Cotton Candy [registerCandy] with a defined Mod ID and Item ID, no [MobEffect]
     */
    fun registerCottonCandy(modID: String, name: String): CandyTooltipItem {
        return registerCandy(modID, name, CandyTranslationString.COTTON, 3, 2)
    }

    /**
     * Registers the Hard Candy item with a tied [MobEffect] for [Int] seconds
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_hard_candy`]
     * @property effect The corresponding [MobEffect] obtained when the candy is eaten
     * @property seconds Amount of seconds applied to the tied [effect]
     *
     * @return Hard Candy [registerCandy] with a defined Mod ID, Item ID, Mob Effect when eaten for a certain amount of seconds
     */
    fun registerHardCandy(modID: String, name: String, effect: Holder<MobEffect>, seconds: Int): CandyTooltipItem {
        return registerCandy(modID, name, CandyTranslationString.HARD, 3, 2, effect, 2, seconds)
    }

    /**
     * Registers the Hard Candy item without any effects
     *
     * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
     * @property name Candy's Item ID [like `white_hard_candy`]
     *
     * @return Hard Candy [registerCandy] with a defined Mod ID and Item ID, no [MobEffect]
     */
    fun registerHardCandy(modID: String, name: String): CandyTooltipItem {
        return registerCandy(modID, name, CandyTranslationString.HARD, 3, 2)
    }

    /**
     * Nothing to see here, *whistles*
     * TODO: Rethink how Ice Cream is done as it's essentially an unused, but registered item, in every version of Softer Pastels.
     */
    fun registerIceCream(modID: String, name: String): Item {
        return Items.registerItem(ResourceLocation.fromNamespaceAndPath(modID, name), Item(Item.Properties().food(FoodProperties.Builder().nutrition(3).saturationModifier(5f).build()))
        )
    }
}