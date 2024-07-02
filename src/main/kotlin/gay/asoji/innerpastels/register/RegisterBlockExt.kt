package gay.asoji.innerpastels.register

import gay.asoji.innerpastels.blocks.FenceGateBlock
import gay.asoji.innerpastels.blocks.Properties.pastelBlock
import gay.asoji.innerpastels.blocks.Properties.pastelCarpet
import gay.asoji.innerpastels.blocks.Properties.pastelFence
import gay.asoji.innerpastels.blocks.Properties.pastelFenceGate
import gay.asoji.innerpastels.blocks.Properties.pastelGlass
import gay.asoji.innerpastels.blocks.Properties.pastelGlassPane
import gay.asoji.innerpastels.blocks.Properties.pastelLeaves
import gay.asoji.innerpastels.blocks.Properties.pastelLight
import gay.asoji.innerpastels.blocks.Properties.pastelLogs
import gay.asoji.innerpastels.blocks.Properties.pastelPowder
import gay.asoji.innerpastels.blocks.Properties.pastelSlab
import gay.asoji.innerpastels.blocks.Properties.pastelStair
import gay.asoji.innerpastels.blocks.Properties.pastelWall
import gay.asoji.innerpastels.blocks.Properties.pastelWool
import gay.asoji.innerpastels.blocks.StairBlock
import gay.asoji.innerpastels.crab.CrabInTheCode
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

/**
 * Block Extensions code for registering Blocks
 */

// a lot of this was written by storm so if there's shit that could be reused/abstracted, yell at her, not me - asoji

/**
 * Registers a block to the Minecraft registry with the resource location set to the Mod ID
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun Block.registerBlock(modID: String, name: String): Block =
    Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(modID, name), this)

/**
 * Registers an item to the Minecraft registry with the resource location set to the Mod ID
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the item
 * @return [BlockItem]
 */
fun Block.registerBlockItem(modID: String, name: String): BlockItem = Registry.register(
    BuiltInRegistries.ITEM,
    ResourceLocation.fromNamespaceAndPath(modID, name),
    BlockItem(this, Item.Properties())
)

/**
 * Registers a block & item to the Minecraft registry with the resource location set to the Mod ID
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block] and [BlockItem]
 */
fun Block.registerBlockWithItem(modID: String, name: String): Block {
    this.registerBlockItem(modID, name)
    return this.registerBlock(modID, name)
}

/**
 * Registers a stained-glass block copying the settings of white stained-glass with the color based on a [DyeColor]
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun DyeColor.registerGlassBlock(modID: String, name: String): Block =
    StainedGlassBlock(this, pastelGlass().mapColor(this)).registerBlock(modID, name)

/**
 * Registers a stained-glass pane block copying the settings of white stained-glass with the color based on a [DyeColor]
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun DyeColor.registerGlassPaneBlock(modID: String, name: String): Block =
    StainedGlassPaneBlock(this, pastelGlassPane().mapColor(this)).registerBlock(modID, name)

/**
 * Registers a pastel block with an accompanying item with the color based on a [DyeColor]
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun DyeColor.registerPastelBlock(modID: String, name: String): Block = Block(pastelBlock().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers a pastel block with an accompanying item with the color based on a [MapColor]
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun MapColor.registerPastelBlock(modID: String, name: String): Block = Block(pastelBlock().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers a pastel powder block with an accompanying item with the color based on a [DyeColor], paired with an accompanying normal pastel block when touched with water
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @property resultPastelBlock the accompanying normal pastel block
 * @return [ConcretePowderBlock]
 */
fun DyeColor.registerPastelPowderBlock(modID: String, name: String, resultPastelBlock: Block): Block =
    ConcretePowderBlock(resultPastelBlock, pastelPowder().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers a pastel powder block with an accompanying item with the color based on a [MapColor], paired with an accompanying normal pastel block when touched with water
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @property resultPastelBlock the accompanying normal pastel block
 * @return [ConcretePowderBlock]
 */
fun MapColor.registerPastelPowderBlock(modID: String, name: String, resultPastelBlock: Block): Block =
    ConcretePowderBlock(resultPastelBlock, pastelPowder().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers a pastel fence block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [FenceBlock]
 */
fun DyeColor.registerPastelFenceBlock(modID: String, name: String): Block =
    FenceBlock(pastelFence().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers a pastel fence block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [FenceBlock]
 */
fun MapColor.registerPastelFenceBlock(modID: String, name: String): Block =
    FenceBlock(pastelFence().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel fence gate block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [FenceGateBlock]
 */
fun DyeColor.registerPastelFenceGateBlock(modID: String, name: String): Block =
    FenceGateBlock(pastelFenceGate().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel fence gate block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [FenceGateBlock]
 */
fun MapColor.registerPastelFenceGateBlock(modID: String, name: String): Block =
    FenceGateBlock(pastelFenceGate().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel wall block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [WallBlock]
 */
fun DyeColor.registerPastelWallBlock(modID: String, name: String): Block =
    WallBlock(pastelWall().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel wall block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [WallBlock]
 */
fun MapColor.registerPastelWallBlock(modID: String, name: String): Block =
    WallBlock(pastelWall().mapColor(this)).registerBlockWithItem(modID, name)

// i'm going to cry
// fucking same bestie
// feb 9th 2024, lets say asoji learned about the CTRL D bind from Storm and feels fucking stupid

/**
 * Registers the pastel slab block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [SlabBlock]
 */
fun DyeColor.registerPastelSlabBlock(modID: String, name: String): Block =
    SlabBlock(pastelSlab().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel slab block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [SlabBlock]
 */
fun MapColor.registerPastelSlabBlock(modID: String, name: String): Block =
    SlabBlock(pastelSlab().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel stair block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [StairBlock]
 */
fun DyeColor.registerPastelStairsBlock(modID: String, name: String): Block = StairBlock(
    Blocks.STONE_STAIRS.defaultBlockState(),
    pastelStair().mapColor(this)
).registerBlockWithItem(modID, name)

/**
 * Registers the pastel stair block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [StairBlock]
 */
fun MapColor.registerPastelStairsBlock(modID: String, name: String): Block = StairBlock(
    Blocks.STONE_STAIRS.defaultBlockState(),
    pastelStair().mapColor(this)
).registerBlockWithItem(modID, name)

/**
 * Registers the pastel wool block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun DyeColor.registerWoolBlock(modID: String, name: String): Block = Block(pastelWool().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel wool block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun MapColor.registerWoolBlock(modID: String, name: String): Block = Block(pastelWool().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel light block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun DyeColor.registerLightBlock(modID: String, name: String): Block = Block(pastelLight().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel light block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun MapColor.registerLightBlock(modID: String, name: String): Block = Block(pastelLight().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * @suppress
 */
object crab1 { val crab2 = CrabInTheCode.crabDeezNuts("hey, it felt comfortable here between the light and the carpet") }

/**
 * Registers the pastel carpet block with an accompanying item with a chosen [DyeColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun DyeColor.registerCarpetBlock(modID: String, name: String): Block =
    CarpetBlock(pastelCarpet().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel carpet block with an accompanying item with a chosen [MapColor] to be shown on a map
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [Block]
 */
fun MapColor.registerCarpetBlock(modID: String, name: String): Block =
    CarpetBlock(pastelCarpet().mapColor(this)).registerBlockWithItem(modID, name)

/**
 * Registers the pastel log blocks with an accompanying item with a chosen [MapColor] to be shown on a map\
 * @property modID The namespace for your mod to be used with, like `SofterPastels.MOD_ID`
 * @property name the ID of the block
 * @return [RotatedPillarBlock]
 */
fun MapColor.registerLogBlock(modID: String, name: String): Block =
    RotatedPillarBlock(pastelLogs().mapColor(this)).registerBlockWithItem(modID, name).apply { FlammableBlockRegistry.getDefaultInstance().add(this, 5, 5) }

// TODO: Document this shit
fun DyeColor.registerLogBlock(modID: String, name: String): Block =
    RotatedPillarBlock(pastelLogs().mapColor(this)).registerBlockWithItem(modID, name).apply { FlammableBlockRegistry.getDefaultInstance().add(this, 5, 5) }

fun MapColor.registerLeavesBlock(modID: String, name: String, emissiveRenderingState: BlockBehaviour.StatePredicate): Block =
    LeavesBlock(pastelLeaves().mapColor(this).hasPostProcess(emissiveRenderingState).emissiveRendering(emissiveRenderingState)).registerBlockWithItem(modID, name).apply { FlammableBlockRegistry.getDefaultInstance().add(this, 30, 60) }

fun DyeColor.registerLeavesBlock(modID: String, name: String, emissiveRenderingState: BlockBehaviour.StatePredicate): Block =
    LeavesBlock(pastelLeaves().mapColor(this).hasPostProcess(emissiveRenderingState).emissiveRendering(emissiveRenderingState)).registerBlockWithItem(modID, name).apply { FlammableBlockRegistry.getDefaultInstance().add(this, 30, 60) }
