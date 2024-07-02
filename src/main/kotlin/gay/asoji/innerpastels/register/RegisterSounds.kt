package gay.asoji.innerpastels.register

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Item
//import net.minecraft.world.item.RecordItem

/**
 * Quickly register sound-related stuff, like a music disc or sound event.
 */
object RegisterSounds {
    // TODO: Work on docs for this later, not in the mood to do it right now
    fun registerSoundEvent(modID: String, name: String): SoundEvent {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(modID, name), SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modID, name)))
    }

    // waiting for data generator for this one, there's a probable chance of it happening
//    fun registerMusicDisc(modID: String, name: String, analogOutput: Int, soundEvent: SoundEvent, seconds: Int): RecordItem {
//        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modID, name), RecordItem(analogOutput, soundEvent, Item.Properties().stacksTo(1), seconds))
//    }
}