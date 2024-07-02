package gay.asoji.innerpastels.datagen

import gay.asoji.innerpastels.crab.CrabInTheCode
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RotatedPillarBlock

/**
 * Model Generators used for Model Data Generation, creating required JSON models for certain blocks, meant to be used with your ModelProvider datagen.
 */
object ModelGenerators {
    /**
     * Creates a slab model based off another block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsModelProvider(generator: FabricDataOutput) : FabricModelProvider(generator) {
     *     override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators) {
     *         createSlabs(SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_SLAB_BLOCK, blockStateModelGenerator)
     *     }
     * }
     * ```
     *
     * @property block The referenced block
     * @property slab The target slab block
     * @property blockStateModelGenerator The Block Model Generator for your block
     */
    fun createSlabs(block: Block, slab: Block, blockStateModelGenerator: BlockModelGenerators) {
        val texturedModel = TexturedModel.CUBE[block]
        val fullSlabModel = ModelLocationUtils.getModelLocation(block)
        val bottomSlab: ResourceLocation = ModelTemplates.SLAB_BOTTOM.create(
            slab,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val topSlab: ResourceLocation = ModelTemplates.SLAB_TOP.create(
            slab,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.blockStateOutput.accept(
            BlockModelGenerators.createSlab(
                slab,
                bottomSlab,
                topSlab,
                fullSlabModel
            )
        )
        blockStateModelGenerator.delegateItemModel(slab, bottomSlab)
    }

    /**
     * Creates a stair model based off another block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsModelProvider(generator: FabricDataOutput) : FabricModelProvider(generator) {
     *     override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators) {
     *         createStairs(SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_STAIR_BLOCK, blockStateModelGenerator)
     *     }
     * }
     * ```
     *
     * @property block The referenced block
     * @property stairs The target stair block
     * @property blockStateModelGenerator The Block Model Generator for your block
     */
    fun createStairs(block: Block, stairs: Block, blockStateModelGenerator: BlockModelGenerators) {
        val texturedModel = TexturedModel.CUBE[block]
        val innerStairs: ResourceLocation = ModelTemplates.STAIRS_INNER.create(
            stairs,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val straightStairs: ResourceLocation = ModelTemplates.STAIRS_STRAIGHT.create(
            stairs,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        CrabInTheCode.crabDeezNuts("can someone help me up the stairs? thaaaaanks")
        val outerStairs: ResourceLocation = ModelTemplates.STAIRS_OUTER.create(
            stairs,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.blockStateOutput.accept(
            BlockModelGenerators.createStairs(
                stairs,
                innerStairs,
                straightStairs,
                outerStairs
            )
        )
        blockStateModelGenerator.delegateItemModel(stairs, straightStairs)
    }

    /**
     * Creates a fence model based off another block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsModelProvider(generator: FabricDataOutput) : FabricModelProvider(generator) {
     *     override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators) {
     *         createFence(SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_FENCE_BLOCK, blockStateModelGenerator)
     *     }
     * }
     * ```
     *
     * @property block The referenced block
     * @property fence The target fence block
     * @property blockStateModelGenerator The Block Model Generator for your block
     */
    fun createFence(block: Block, fence: Block, blockStateModelGenerator: BlockModelGenerators) {
        val texturedModel = TexturedModel.CUBE[block]
        val fencePost: ResourceLocation = ModelTemplates.FENCE_POST.create(
            fence,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val fenceSide: ResourceLocation = ModelTemplates.FENCE_SIDE.create(
            fence,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.blockStateOutput.accept(
            BlockModelGenerators.createFence(
                fence,
                fencePost,
                fenceSide,
            )
        )
        val fenceInventoryModel: ResourceLocation = ModelTemplates.FENCE_INVENTORY.create(
            fence,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.delegateItemModel(fence, fenceInventoryModel)
    }

    /**
     * Creates a fence gate model based off another block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsModelProvider(generator: FabricDataOutput) : FabricModelProvider(generator) {
     *     override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators) {
     *         createFenceGate(SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_FENCE_GATE_BLOCK, blockStateModelGenerator)
     *     }
     * }
     * ```
     *
     * @property block The referenced block
     * @property fenceGate The target fence gate
     * @property blockStateModelGenerator The Block Model Generator for your block
     */
    fun createFenceGate(block: Block, fenceGate: Block, blockStateModelGenerator: BlockModelGenerators) {
        val texturedModel = TexturedModel.CUBE[block]
        val fenceGateOpen: ResourceLocation = ModelTemplates.FENCE_GATE_OPEN.create(
            fenceGate,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val fenceGateClosed: ResourceLocation = ModelTemplates.FENCE_GATE_CLOSED.create(
            fenceGate,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val fenceGateWallOpen: ResourceLocation = ModelTemplates.FENCE_GATE_WALL_OPEN.create(
            fenceGate,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val fenceGateWallClose: ResourceLocation = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(
            fenceGate,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.blockStateOutput.accept(
            BlockModelGenerators.createFenceGate(
                fenceGate,
                fenceGateOpen,
                fenceGateClosed,
                fenceGateWallOpen,
                fenceGateWallClose,
                true
            )
        )
        blockStateModelGenerator.delegateItemModel(fenceGate, fenceGateClosed)
    }

    /**
     * Creates a wall model based off another block
     *
     * Example:
     * ```kotlin
     * class SofterPastelsModelProvider(generator: FabricDataOutput) : FabricModelProvider(generator) {
     *     override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators) {
     *         createWall(SofterPastelsBlocks.WHITE_PASTEL_BLOCK, SofterPastelsBlocks.WHITE_PASTEL_WALL_BLOCK, blockStateModelGenerator)
     *     }
     * }
     * ```
     *
     * @property block The referenced block
     * @property wall The target wall
     * @property blockStateModelGenerator The Block Model Generator for your block
     */
    fun createWall(block: Block, wall: Block, blockStateModelGenerator: BlockModelGenerators) {
        val texturedModel = TexturedModel.CUBE[block]
        val wallPost: ResourceLocation = ModelTemplates.WALL_POST.create(
            wall,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val wallLowSide: ResourceLocation = ModelTemplates.WALL_LOW_SIDE.create(
            wall,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        val wallTallSide: ResourceLocation = ModelTemplates.WALL_TALL_SIDE.create(
            wall,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.blockStateOutput.accept(
            BlockModelGenerators.createWall(
                wall,
                wallPost,
                wallLowSide,
                wallTallSide,
            )
        )
        val wallInventoryModel: ResourceLocation = ModelTemplates.WALL_INVENTORY.create(
            wall,
            texturedModel.mapping,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.delegateItemModel(wall, wallInventoryModel)
    }

    /**
     * Creates a pillar model
     *
     * Example:
     * ```kotlin
     * class DesolatedPastelsModelProvider(generator: FabricDataOutput) : FabricModelProvider(generator) {
     *     override fun generateBlockStateModels(blockStateModelGenerator: BlockModelGenerators) {
     *         ModelGenerators.createPillar(DesolatedPastelsBlocks.LIGHT_GREEN_LOG as RotatedPillarBlock, blockStateModelGenerator)
     *     }
     * }
     * ```
     *
     * @property pillarBlock The target pillar block
     * @property blockStateModelGenerator The Block Model Generator for your block
     */
    fun createPillar(pillarBlock: RotatedPillarBlock, blockStateModelGenerator: BlockModelGenerators) {
        val texture = TextureMapping.logColumn(pillarBlock)
        val pillarMain: ResourceLocation = ModelTemplates.CUBE_COLUMN.create(
            pillarBlock,
            texture,
            blockStateModelGenerator.modelOutput
        )
        val pillarHorizontal: ResourceLocation = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(
            pillarBlock,
            texture,
            blockStateModelGenerator.modelOutput
        )
        blockStateModelGenerator.blockStateOutput.accept(
            BlockModelGenerators.createRotatedPillarWithHorizontalVariant(pillarBlock, pillarMain, pillarHorizontal)
        )
    }
}