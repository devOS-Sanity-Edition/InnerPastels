package gay.asoji.innerpastels.tags

import gay.asoji.innerpastels.register.RegisterTags.createItemTag

/**
 * A bunch of items tags meant to be used for tagging items to their respective tags.
 *
 * Now try saying tags 100 times over.
 */
object InnerPastelsItemTags {
    // Softer
    /**
     * Items assigned with this tag are deemed as pastel block items to whatever looks for `#innerpastels:pastel_blocks`
     * @return [InnerPastelsItemTags.PASTEL_BLOCKS]
     */
    val PASTEL_BLOCKS = createItemTag("pastel_blocks")

    /**
     * Items assigned with this tag are deemed as pastel powder block items to whatever looks for `#innerpastels:pastel_powder_blocks`
     * @return [InnerPastelsItemTags.PASTEL_POWDER_BLOCKS]
     */
    val PASTEL_POWDER_BLOCKS = createItemTag("pastel_powder_blocks")

    /**
     * Items assigned with this tag are deemed as pastel fence block items to whatever looks for `#innerpastels:fences`
     * @return [InnerPastelsItemTags.FENCES]
     */
    val FENCES = createItemTag("fences")

    /**
     * Items assigned with this tag are deemed as pastel fence gates block items to whatever looks for `#innerpastels:fence_gates`
     * @return [InnerPastelsItemTags.FENCE_GATES]
     */
    val FENCE_GATES = createItemTag("fence_gates")

    /**
     * Items assigned with this tag are deemed as pastel wall block items to whatever looks for `#innerpastels:walls`
     * @return [InnerPastelsItemTags.WALLS]
     */
    val WALLS = createItemTag("walls")

    /**
     * Items assigned with this tag are deemed as pastel slabs block items to whatever looks for `#innerpastels:slabs`
     * @return [InnerPastelsItemTags.SLABS]
     */
    val SLABS = createItemTag("slabs")

    /**
     * Items assigned with this tag are deemed as pastel stair block items to whatever looks for `#innerpastels:stairs`
     * @return [InnerPastelsItemTags.STAIRS]
     */
    val STAIRS = createItemTag("stairs")

    /**
     * Items assigned with this tag are deemed as pastel wool block items to whatever looks for `#innerpastels:wools`
     * @return [InnerPastelsItemTags.WOOLS]
     */
    val WOOLS = createItemTag("wools")

    /**
     * Items assigned with this tag are deemed as pastel lights block items to whatever looks for `#innerpastels:lights`
     * @return [InnerPastelsItemTags.LIGHTS]
     */
    val LIGHTS = createItemTag("lights")

    /**
     * Items assigned with this tag are deemed as pastel carpets block items to whatever looks for `#innerpastels:carpets`
     * @return [InnerPastelsItemTags.CARPETS]
     */
    val CARPETS = createItemTag("carpets")

    /**
     * Items assigned with this tag are deemed as pastel glass block items to whatever looks for `#innerpastels:glass_blocks`
     * @return [InnerPastelsItemTags.GLASS_BLOCKS]
     */
    val GLASS_BLOCKS = createItemTag("glass_blocks")

    /**
     * Items assigned with this tag are deemed as pastel glass pane block items to whatever looks for `#innerpastels:glass_panes`
     * @return [InnerPastelsItemTags.GLASS_PANES]
     */
    val GLASS_PANES = createItemTag("glass_panes")


    /**
     * Items assigned with this tag are deemed as pastel powder items to whatever looks for `#innerpastels:powders`
     * @return [InnerPastelsItemTags.GLASS_PANES]
     */
    val POWDERS = createItemTag("powders")

    /**
     * Items assigned with this tag are deemed as taffy items to whatever looks for `#innerpastels:taffies`
     * @return [InnerPastelsItemTags.GLASS_PANES]
     */
    val TAFFIES = createItemTag("taffies")

    /**
     * Items assigned with this tag are deemed as cotton candy items to whatever looks for `#innerpastels:cotton_candies`
     * @return [InnerPastelsItemTags.GLASS_PANES]
     */
    val COTTON_CANDIES = createItemTag("cotton_candies")

    /**
     * Items assigned with this tag are deemed as hard candy items to whatever looks for `#innerpastels:hard_candies`
     * @return [InnerPastelsItemTags.GLASS_PANES]
     */
    val HARD_CANDIES = createItemTag("hard_candies")

    /**
     * Items assigned with this tag are deemed as ice cream items to whatever looks for `#innerpastels:ice_cream`
     * @return [InnerPastelsItemTags.GLASS_PANES]
     */
    val ICE_CREAM = createItemTag("ice_cream")

    // Desolated
    val ORES = createItemTag("ores")
    val LOGS = createItemTag("logs")
    val PLANKS = createItemTag("planks")
    val LEAVES = createItemTag("leaves")
    val DIRT = createItemTag("dirt")
    val GRASS = createItemTag("grass")
    val STONES = createItemTag("stones")
}