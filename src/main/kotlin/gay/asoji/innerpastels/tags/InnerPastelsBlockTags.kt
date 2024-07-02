package gay.asoji.innerpastels.tags

import gay.asoji.innerpastels.register.RegisterTags.createBlockTag

/**
 * A bunch of block tags meant to be used for tagging blocks to their respective tags.
 *
 * Now try saying tags 100 times over.
 */
object InnerPastelsBlockTags {
    // A bunch of these tags are *probably* not needed but do they hurt to make? no not rly

    /**
     * Blocks assigned with this tag are deemed as portal blocks to whatever looks for `#innerpastels:valid_portal`
     * @return [InnerPastelsBlockTags.VALID_PORTAL]
     */
    val VALID_PORTAL = createBlockTag("valid_portal")

    // Softer
    /**
     * Blocks assigned with this tag are deemed as pastel blocks to whatever looks for `#innerpastels:pastel_blocks`
     * @return [InnerPastelsBlockTags.PASTEL_BLOCKS]
     */
    val PASTEL_BLOCKS = createBlockTag("pastel_blocks")

    /**
     * Blocks assigned with this tag are deemed as pastel powder blocks to whatever looks for `#innerpastels:pastel_powder_blocks`
     * @return [InnerPastelsBlockTags.PASTEL_POWDER_BLOCKS]
     */
    val PASTEL_POWDER_BLOCKS = createBlockTag("pastel_powder_blocks")

    /**
     * Blocks assigned with this tag are deemed as pastel fences to whatever looks for `#innerpastels:fences`
     * @return [InnerPastelsBlockTags.FENCES]
     */
    val FENCES = createBlockTag("fences")

    /**
     * Blocks assigned with this tag are deemed as pastel fence gates to whatever looks for `#innerpastels:fence_gates`
     * @return [InnerPastelsBlockTags.FENCE_GATES]
     */
    val FENCE_GATES = createBlockTag("fence_gates")

    /**
     * Blocks assigned with this tag are deemed as pastel walls to whatever looks for `#innerpastels:walls`
     * @return [InnerPastelsBlockTags.WALLS]
     *
     * By the way, I am living in your walls. **Boo.**
     */
    val WALLS = createBlockTag("walls")

    /**
     * Blocks assigned with this tag are deemed as pastel slabs to whatever looks for `#innerpastels:slabs`
     * @return [InnerPastelsBlockTags.SLABS]
     */
    val SLABS = createBlockTag("slabs")

    /**
     * Blocks assigned with this tag are deemed as pastel stairs to whatever looks for `#innerpastels:stairs`
     * @return [InnerPastelsBlockTags.STAIRS]
     */
    val STAIRS = createBlockTag("stairs")

    /**
     * Blocks assigned with this tag are deemed as pastel wools to whatever looks for `#innerpastels:wools`
     * @return [InnerPastelsBlockTags.WOOLS]
     */
    val WOOLS = createBlockTag("wools")

    /**
     * Blocks assigned with this tag are deemed as pastel lights to whatever looks for `#innerpastels:lights`
     * @return [InnerPastelsBlockTags.LIGHTS]
     */
    val LIGHTS = createBlockTag("lights")

    /**
     * Blocks assigned with this tag are deemed as pastel carpets to whatever looks for `#innerpastels:carpets`
     * @return [InnerPastelsBlockTags.CARPETS]
     */
    val CARPETS = createBlockTag("carpets")

    /**
     * Blocks assigned with this tag are deemed as pastel glass blocks to whatever looks for `#innerpastels:glass_blocks`
     * @return [InnerPastelsBlockTags.GLASS_BLOCKS]
     */
    val GLASS_BLOCKS = createBlockTag("glass_blocks")

    /**
     * Blocks assigned with this tag are deemed as pastel glass panes to whatever looks for `#innerpastels:glass_panes`
     * @return [InnerPastelsBlockTags.GLASS_PANES]
     */
    val GLASS_PANES = createBlockTag("glass_panes")

    // Desolated
    // **UNDOCUMENTED FOR NOW**
    val ORES = createBlockTag("ores")
    val LOGS = createBlockTag("logs")
    val PLANKS = createBlockTag("planks")
    val LEAVES = createBlockTag("leaves")
    val DIRT = createBlockTag("dirt")
    val GRASS = createBlockTag("grass")
    val STONES = createBlockTag("stones")
}