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
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.8F)
    }

    /**
     * Default properties for Pastel Powder Block, being a copy of WHITE_CONCRETE_POWDER
     */
    fun pastelPowder(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.SNARE)
            .strength(0.5F)
            .sound(SoundType.SAND)
    }

    /**
     * Default properties for Pastel Glass Pane, being a copy of WHITE_STAINED_GLASS, with vision not being blocked, and can't suffocate
     */
    fun pastelGlassPane(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.HAT)
            .strength(0.3F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .requiresCorrectToolForDrops()
            .isViewBlocking(Blocks::never)
            .isSuffocating(Blocks::never)
    }

    /**
     * Default properties for Pastel Fence
     */
    fun pastelFence(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(2.0F, 6.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Fence Gate
     */
    fun pastelFenceGate(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(2.0F, 6.0F)
            .forceSolidOn()
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Wall
     */
    fun pastelWall(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(1.5F, 6.0F)
            .forceSolidOn() // i saw this was there for STONE_BRICKS so it's needed
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Slab
     */
    fun pastelSlab(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(2.0F, 6.0F)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Stair
     */
    fun pastelStair(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(1.5F, 6.0F)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Wool
     */
    fun pastelWool(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.GUITAR)
            .strength(0.8F)
            .sound(SoundType.WOOL)
            .ignitedByLava()
    }

    /**
     * Default properties for Pastel Light
     */
    fun pastelLight(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.PLING)
            .strength(0.3F)
            .sound(SoundType.GLASS)
            .lightLevel { blockStatex: BlockState? -> 15 }
            .isRedstoneConductor(Blocks::never)
    }

    /**
     * Default properties for Pastel Carpet
     */
    fun pastelCarpet(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .strength(0.1F)
            .sound(SoundType.WOOL)
            .ignitedByLava()
    }

    /**
     * Default properties for Pastel Glass
     */
    fun pastelGlass(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.HAT)
            .strength(0.3F)
            .sound(SoundType.GLASS)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .isRedstoneConductor(Blocks::never)
            .requiresCorrectToolForDrops()
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
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
        return BlockBehaviour.Properties.of()
            .strength(0.2F)
            .randomTicks()
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)
    }

    /**
     * Default properties for Pastel Brightened Leaves
     */
    fun pastelBrightenedLeaves(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .strength(0.2F)
            .randomTicks()
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)
            .lightLevel { blockStatex: BlockState? -> 5 }
            .hasPostProcess(Blocks::always).emissiveRendering(Blocks::always)
    }

    /**
     * Default properties for Pastel Log
     */
    fun pastelLogs(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava()
    }

    /**
     * Default properties for Pastel Plank
     */
    fun pastelPlanks(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava()
    }

    /**
     * Default properties for Pastel Sand
     */
    fun pastelSand(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.SNARE)
            .strength(0.5F)
            .sound(SoundType.SAND)
    }

    /**
     * Default properties for Pastel Ore
     */
    fun pastelOre(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(3.0F, 3.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
    }

    /**
     * Default properties for Pastel Dirt
     */
    fun pastelDirt(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .strength(0.5F)
            .sound(SoundType.GRAVEL)
    }

    /**
     * Default properties for Pastel Grass
     */
    fun pastelGrass(): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .randomTicks()
            .strength(0.6F)
            .sound(SoundType.GRASS)
    }

}
