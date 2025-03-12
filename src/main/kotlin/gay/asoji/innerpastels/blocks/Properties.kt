package gay.asoji.innerpastels.blocks

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.PushReaction

/**
 * A list of Block Properties, mostly copyOf's, to be used with making new blocks.
 */
object Properties {
    /**
     * Default properties for Pastel Block, being a copy of WHITE_CONCRETE
     */
    fun pastelBlock(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Powder Block, being a copy of WHITE_CONCRETE_POWDER
     */
    fun pastelPowder(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER)
    }

    /**
     * Default properties for Pastel Glass Pane, being a copy of WHITE_STAINED_GLASS, with vision not being blocked, and can't suffocate
     */
    fun pastelGlassPane(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE)
    }

    /**
     * Default properties for Pastel Fence
     */
    fun pastelFence(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Fence Gate
     */
    fun pastelFenceGate(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Wall
     */
    fun pastelWall(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL)
    }

    /**
     * Default properties for Pastel Slab
     */
    fun pastelSlab(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)
    }

    /**
     * Default properties for Pastel Stair
     */
    fun pastelStair(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)
    }

    /**
     * Default properties for Pastel Wool
     */
    fun pastelWool(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
    }

    /**
     * Default properties for Pastel Light
     */
    fun pastelLight(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE)
            .lightLevel { blockStatex: BlockState? -> 15 }
            .isRedstoneConductor(Blocks::never)
    }

    /**
     * Default properties for Pastel Carpet
     */
    fun pastelCarpet(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET)
    }

    /**
     * Default properties for Pastel Glass
     */
    fun pastelGlass(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)
    }

    // **UNUSED**
    fun pastelCrops(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY)
    }

    /**
     * Default properties for Pastel Leaves
     */
    fun pastelLeaves(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
    }

    /**
     * Default properties for Pastel Brightened Leaves
     */
    fun pastelBrightenedLeaves(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            .lightLevel { blockStatex: BlockState? -> 5 }
            .hasPostProcess(Blocks::always).emissiveRendering(Blocks::always)
    }

    /**
     * Default properties for Pastel Log
     */
    fun pastelLogs(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
    }

    /**
     * Default properties for Pastel Plank
     */
    fun pastelPlanks(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    }

    /**
     * Default properties for Pastel Sand
     */
    fun pastelSand(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
    }

    /**
     * Default properties for Pastel Ore
     */
    fun pastelOre(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE)
    }

    /**
     * Default properties for Pastel Dirt
     */
    fun pastelDirt(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
    }

    /**
     * Default properties for Pastel Grass
     */
    fun pastelGrass(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
    }

}
