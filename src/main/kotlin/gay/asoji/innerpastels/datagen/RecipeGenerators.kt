package gay.asoji.innerpastels.datagen

import gay.asoji.innerpastels.crab.CrabInTheCode
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.*
import net.minecraft.data.recipes.RecipeProvider.has
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike

/**
 * Recipe Generators used for Recipe Generation via Fabric Recipe Provider, generating the required recipe JSONs, meant to be used with your RecipeProvider datagen.
 *
 * im craving tomato soup now
 */
object RecipeGenerators {
    // There's some naming inconsistencies in this file, I think, I'm a bit too tired to deal with it, I'll fix it
    // either before launching Softer Pastels v0.6.0, or after... we'll see

    // Generally, the order is register[TypeOfCrafting][PastelOrNone][NameOfItem][Item,Block,OrFoodName][VorNone]
    // is it lengthy? yes! is it verbose? yeah! does it tell you right away what it is? YES! and that helps imo

    // Documentation later

    /**
     * Registers a Smelting recipe for Pastel Glass
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     private val WHITE_PASTEL_GLASS_SMELTABLES: List<ItemLike> = listOf(SofterPastelsBlocks.WHITE_PASTEL_POWDER_BLOCK)
     *
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerSmeltingPastelGlass(exporter, WHITE_PASTEL_GLASS_SMELTABLES, GlassBlocks.WHITE_GLASS_ITEM, "pastel_glass")
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property smeltableList A List of what can be used as an input item to be smelted down into a resulting [outputPastelGlassItem]
     * @property outputPastelGlassItem The resulting Glass Item from your input [smeltableList]
     * @property group A string that defines what group your recipe belongs to
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the smelting recipe for making Pastel Glass
     */
    fun registerSmeltingPastelGlass(
        exporter: RecipeOutput,
        smeltableList: List<ItemLike>,
        outputPastelGlassItem: ItemLike,
        group: String
    ) {
        return FabricRecipeProvider.oreSmelting(
            exporter,
            smeltableList,
            RecipeCategory.DECORATIONS,
            outputPastelGlassItem,
            0.7f,
            200,
            group
        )
    }

    /**
     * Registers a Smelting recipe for Pastel Hard Candy
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     private val WHITE_PASTEL_HARD_CANDY_SMELTABLES: List<ItemLike> = listOf(SofterPastelsItems.WHITE_TAFFY)
     *
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerSmeltingPastelHardCandy(exporter, WHITE_PASTEL_HARD_CANDY_SMELTABLES, SofterPastelsItems.WHITE_HARD_CANDY, "pastel_hard_candy")
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property smeltableList A [List<ItemLike>] of what can be used as an input item to be smelted down into a resulting [outputPastelHardCandyItem]
     * @property outputPastelGlassItem The resulting Pastel Hard Candy Item from your input [smeltableList]
     * @property group A string that defines what group your recipe belongs to
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the smelting recipe for making Pastel Hard Candy
     */
    fun registerSmeltingPastelHardCandy(
        exporter: RecipeOutput,
        smeltableList: List<ItemLike>,
        outputPastelHardCandyItem: ItemLike,
        group: String
    ) {
        return FabricRecipeProvider.oreSmelting(
            exporter,
            smeltableList,
            RecipeCategory.FOOD,
            outputPastelHardCandyItem,
            1f,
            200,
            group
        )
    }

    /**
     * Registers a Shapeless Crafting Recipe for Pastel Powder Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelPowderBlock(exporter, SofterPastelsItems.WHITE_POWDER, SofterPastelsBlocks.WHITE_PASTEL_POWDER_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputPastelPowderBlock The Pastel Powder Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Powder Block
     */
    fun registerCraftingPastelPowderBlock(
        exporter: RecipeOutput,
        inputPastelPowderItem: ItemLike,
        outputPastelPowderBlock: ItemLike
    ) {
        CrabInTheCode.crabDeezNuts("snadcastle :3")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, outputPastelPowderBlock, 8)
            .requires(Items.SAND)
            .requires(Items.SAND)
            .requires(Items.SAND)
            .requires(Items.SAND)
            .requires(Items.GRAVEL)
            .requires(Items.GRAVEL)
            .requires(Items.GRAVEL)
            .requires(Items.GRAVEL)
            .requires(inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(Items.SAND), RecipeProvider.has(Items.SAND))
            .unlockedBy(RecipeProvider.getHasName(Items.GRAVEL), RecipeProvider.has(Items.GRAVEL))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelPowderBlock), RecipeProvider.has(outputPastelPowderBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Powder Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelSlabBlock(exporter, SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_SLAB_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelBlock The Powder Block Item used for input
     * @property outputPastelSlabBlock The Pastel Slab Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Slab Block
     */
    fun registerCraftingPastelSlabBlock(
        exporter: RecipeOutput,
        inputPastelBlock: ItemLike,
        outputPastelSlabBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, outputPastelSlabBlock, 6)
            .pattern("AAA")
            .define('A', inputPastelBlock)
            .unlockedBy(RecipeProvider.getHasName(inputPastelBlock), RecipeProvider.has(inputPastelBlock))
            .unlockedBy(RecipeProvider.getHasName(outputPastelSlabBlock), RecipeProvider.has(outputPastelSlabBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Stair Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelStairBlock(exporter, SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_STAIR_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelBlock The Powder Block Item used for input
     * @property outputPastelStairBlock The Pastel Stair Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Stair Block
     */
    fun registerCraftingPastelStairBlock(
        exporter: RecipeOutput,
        inputPastelBlock: ItemLike,
        outputPastelStairBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, outputPastelStairBlock, 4)
            .pattern("A  ")
            .pattern("AA ")
            .pattern("AAA")
            .define('A', inputPastelBlock)
            .unlockedBy(RecipeProvider.getHasName(inputPastelBlock), RecipeProvider.has(inputPastelBlock))
            .unlockedBy(RecipeProvider.getHasName(outputPastelStairBlock), RecipeProvider.has(outputPastelStairBlock))
            .save(exporter)
    }

    /**
     * Registers a Shapeless Crafting Recipe for Pastel Wool Block with Vanilla White Wool
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelWoolBlockV(exporter, SofterPastelsItems.WHITE_POWDER, SofterPastelsBlocks.WHITE_PASTEL_WOOL_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputPastelWoolBlock The Pastel Wool Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Wool Block using Vanilla White Wool
     */
    fun registerCraftingPastelWoolBlockV(
        exporter: RecipeOutput,
        inputPastelPowderItem: ItemLike,
        outputPastelWoolBlock: ItemLike
    ) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, outputPastelWoolBlock, 1)
            .requires(Items.WHITE_WOOL)
            .requires(inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(Items.WHITE_WOOL), RecipeProvider.has(Items.WHITE_WOOL))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelWoolBlock), RecipeProvider.has(outputPastelWoolBlock))
            .save(
                exporter,
                outputPastelWoolBlock.toString().lowercase().removePrefix("block{softerpastels:")
                    .replace("}", "") + "_v"
            )
    }

    /**
     * Registers a Shapeless Crafting Recipe for Pastel Wool Block with White Pastel Wool
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelWoolBlock(exporter, SofterPastelsItems.WHITE_POWDER, SofterPastelsBlocks.WHITE_PASTEL_WOOL_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputPastelWoolBlock The Pastel Wool Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Wool Block using White Pastel Wool
     */
    fun registerCraftingPastelWoolBlock(
        exporter: RecipeOutput,
        inputPastelPowderItem: ItemLike,
        inputPastelWoolBlock: ItemLike,
        outputPastelWoolBlock: ItemLike
    ) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, outputPastelWoolBlock, 1)
            .requires(inputPastelWoolBlock)
            .requires(inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(inputPastelWoolBlock), has(inputPastelWoolBlock))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelWoolBlock), RecipeProvider.has(outputPastelWoolBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Carpet Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelCarpetBlock(exporter, SofterPastelsBlocks.WHITE_PASTEL_WOOL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_CARPET_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelWoolBlock The Pastel Wool Block Item used for input
     * @property outputPastelCarpetBlock The Pastel Carpet Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Carpet Block
     */
    fun registerCraftingPastelCarpetBlock(
        exporter: RecipeOutput,
        inputPastelWoolBlock: ItemLike,
        outputPastelCarpetBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelCarpetBlock, 3)
            .pattern("AA")
            .define('A', inputPastelWoolBlock)
            .unlockedBy(RecipeProvider.getHasName(inputPastelWoolBlock), RecipeProvider.has(inputPastelWoolBlock))
            .unlockedBy(RecipeProvider.getHasName(outputPastelCarpetBlock), RecipeProvider.has(outputPastelCarpetBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Fence Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelFenceBlock(exporter, SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_FENCE_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelBlock The Pastel Block Item used for input
     * @property outputPastelFenceBlock The Pastel Fence Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Fence Block
     */
    fun registerCraftingPastelFenceBlock(
        exporter: RecipeOutput,
        inputPastelBlock: ItemLike,
        outputPastelFenceBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelFenceBlock, 3)
            .pattern("ABA")
            .pattern("ABA")
            .define('A', inputPastelBlock)
            .define('B', Items.STICK)
            .unlockedBy(RecipeProvider.getHasName(Items.STICK), RecipeProvider.has(Items.STICK))
            .unlockedBy(RecipeProvider.getHasName(inputPastelBlock), RecipeProvider.has(inputPastelBlock))
            .unlockedBy(RecipeProvider.getHasName(outputPastelFenceBlock), RecipeProvider.has(outputPastelFenceBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Fence Gate Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelFenceGateBlock(exporter, SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_FENCE_GATE_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelBlock The Pastel Block Item used for input
     * @property outputPastelFenceGateBlock The Pastel Fence Gate Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Fence Gate Block
     */
    fun registerCraftingPastelFenceGateBlock(
        exporter: RecipeOutput,
        inputPastelBlock: ItemLike,
        outputPastelFenceGateBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelFenceGateBlock, 1)
            .pattern("BAB")
            .pattern("BAB")
            .define('A', inputPastelBlock)
            .define('B', Items.STICK)
            .unlockedBy(RecipeProvider.getHasName(Items.STICK), RecipeProvider.has(Items.STICK))
            .unlockedBy(RecipeProvider.getHasName(inputPastelBlock), RecipeProvider.has(inputPastelBlock))
            .unlockedBy(RecipeProvider.getHasName(outputPastelFenceGateBlock), RecipeProvider.has(outputPastelFenceGateBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Wall Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelWallBlock(exporter, SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_FENCE_GATE_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelBlock The Pastel Block Item used for input
     * @property outputPastelWallBlock The Pastel Wall Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Wall Block
     */
    fun registerCraftingPastelWallBlock(
        exporter: RecipeOutput,
        inputPastelBlock: ItemLike,
        outputPastelWallBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelWallBlock, 6)
            .pattern("AAA")
            .pattern("AAA")
            .define('A', inputPastelBlock)
            .unlockedBy(RecipeProvider.getHasName(inputPastelBlock), RecipeProvider.has(inputPastelBlock))
            .unlockedBy(RecipeProvider.getHasName(outputPastelWallBlock), RecipeProvider.has(outputPastelWallBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Glass Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelGlassBlock(exporter, SofterPastelsItems.WHITE_POWDER, GlassBlocks.WHITE_GLASS_ITEM)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputPastelGlassBlock The Pastel Glass Block Item outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Glass Block
     */
    fun registerCraftingPastelGlassBlock(
        exporter: RecipeOutput,
        inputPastelPowderItem: ItemLike,
        outputPastelGlassBlock: ItemLike
    ) {
        CrabInTheCode.crabDeezNuts("oooooooo shiny")
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelGlassBlock, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('A', Items.GLASS)
            .define('B', inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(Items.GLASS), RecipeProvider.has(Items.GLASS))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelGlassBlock), RecipeProvider.has(outputPastelGlassBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Glass Pane Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelGlassPaneBlock(exporter, SofterPastelsItems.WHITE_POWDER, GlassBlocks.WHITE_GLASS_PANE_ITEM)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputPastelGlassPaneBlock The Pastel Glass Pane Block Item outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Glass Pane Block
     */
    fun registerCraftingPastelGlassPaneBlock(
        exporter: RecipeOutput,
        inputPastelPowderItem: ItemLike,
        outputPastelGlassPaneBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelGlassPaneBlock, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('A', Items.GLASS_PANE)
            .define('B', inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(Items.GLASS_PANE), RecipeProvider.has(Items.GLASS_PANE))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelGlassPaneBlock), RecipeProvider.has(outputPastelGlassPaneBlock))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Glass Pane Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *             registerCraftingPastelGlassPaneRectangleBlock(exporter, GlassBlocks.WHITE_GLASS_ITEM, GlassBlocks.WHITE_GLASS_PANE_ITEM)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelGlassBlockItem The Glass Block used for input
     * @property outputPastelGlassPaneBlock The Pastel Glass Pane Block Item outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Glass Pane Block
     */
    fun registerCraftingPastelGlassPaneRectangleBlock(
        exporter: RecipeOutput,
        inputPastelGlassBlockItem: ItemLike,
        outputPastelGlassPaneBlock: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, outputPastelGlassPaneBlock, 16)
            .pattern("AAA")
            .pattern("AAA")
            .define('A', inputPastelGlassBlockItem)
            .unlockedBy(RecipeProvider.getHasName(inputPastelGlassBlockItem), RecipeProvider.has(inputPastelGlassBlockItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelGlassPaneBlock), RecipeProvider.has(outputPastelGlassPaneBlock))
            .save(
                exporter,
                outputPastelGlassPaneBlock.toString().lowercase().removePrefix("block{softerpastels:")
                    .replace("}", "") + "_rect"
            )
    }

    /**
     * Registers a Shapeless Crafting Recipe for Pastel Light Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelLightBlock(exporter, SofterPastelsItems.WHITE_POWDER, SofterPastelsBlocks.WHITE_PASTEL_LIGHT_BLOCK)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputPastelLightBlock The Pastel Light Block outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Light Block
     */
    fun registerCraftingPastelLightBlock(
        exporter: RecipeOutput,
        inputPastelPowderItem: ItemLike,
        outputPastelLightBlock: ItemLike
    ) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, outputPastelLightBlock, 1)
            .requires(Items.GLOWSTONE)
            .requires(inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(Items.GLOWSTONE), RecipeProvider.has(Items.GLOWSTONE))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputPastelLightBlock), RecipeProvider.has(outputPastelLightBlock))
            .save(exporter)
    }

    /**
     * Registers a Shapeless Crafting Recipe for Pastel Taffy
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerPastelTaffyItem(exporter, SofterPastelsItems.WHITE_POWDER, SofterPastelsItems.WHITE_TAFFY)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputPastelPowderItem The Powder Item used for input
     * @property outputTaffyItem The Pastel Taffy Item outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Taffy
     */
    fun registerPastelTaffyItem(exporter: RecipeOutput, inputPastelPowderItem: ItemLike, outputTaffyItem: ItemLike) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, outputTaffyItem, 4)
            .requires(Items.PAPER)
            .requires(Items.HONEYCOMB)
            .requires(Items.SUGAR)
            .requires(inputPastelPowderItem)
            .unlockedBy(RecipeProvider.getHasName(Items.PAPER), RecipeProvider.has(Items.PAPER))
            .unlockedBy(RecipeProvider.getHasName(Items.HONEYCOMB), RecipeProvider.has(Items.HONEYCOMB))
            .unlockedBy(RecipeProvider.getHasName(Items.SUGAR), RecipeProvider.has(Items.SUGAR))
            .unlockedBy(RecipeProvider.getHasName(inputPastelPowderItem), RecipeProvider.has(inputPastelPowderItem))
            .unlockedBy(RecipeProvider.getHasName(outputTaffyItem), RecipeProvider.has(outputTaffyItem))
            .save(exporter)
    }

    /**
     * Registers a Shaped Crafting Recipe for Pastel Glass Pane Block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
     *     override fun buildRecipes(exporter: RecipeOutput) {
     *         registerCraftingPastelCottonCandyItem(exporter, SofterPastelsItems.WHITE_POWDER, SofterPastelsItems.WHITE_COTTON_CANDY)
     *     }
     * }
     * ```
     *
     * @property exporter The RecipeOutput used by your Recipe Provider
     * @property inputTaffyItem The Taffy used for input
     * @property outputCottonCandyItem The Cotton Candy Item outputted
     *
     * @return A JSON file provided by your Recipe Provider datagen that has the shapeless crafting recipe for making Pastel Cotton Candy
     */
    fun registerCraftingPastelCottonCandyItem(
        exporter: RecipeOutput,
        inputTaffyItem: ItemLike,
        outputCottonCandyItem: ItemLike
    ) {
        CrabInTheCode.crabDeezNuts("nom nom nom noms, tasty")
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, outputCottonCandyItem, 2)
            .pattern(" A ")
            .pattern("ABA")
            .pattern(" C ")
            .define('A', Items.STRING)
            .define('B', inputTaffyItem)
            .define('C', Items.STICK)
            .unlockedBy(RecipeProvider.getHasName(Items.STRING), RecipeProvider.has(Items.STRING))
            .unlockedBy(RecipeProvider.getHasName(Items.STICK), RecipeProvider.has(Items.STICK))
            .unlockedBy(RecipeProvider.getHasName(inputTaffyItem), RecipeProvider.has(inputTaffyItem))
            .unlockedBy(RecipeProvider.getHasName(outputCottonCandyItem), RecipeProvider.has(outputCottonCandyItem))
            .save(exporter)
    }
}