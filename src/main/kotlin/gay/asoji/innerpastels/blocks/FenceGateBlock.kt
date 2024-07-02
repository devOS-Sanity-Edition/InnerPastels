package gay.asoji.innerpastels.blocks

import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.state.properties.WoodType

/**
 * Normal Fence Gate Block class, inheriting from Vanilla's FenceGateBlock
 */
class FenceGateBlock(properties: Properties) : FenceGateBlock(WoodType.OAK, properties)