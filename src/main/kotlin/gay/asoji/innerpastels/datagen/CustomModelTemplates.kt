package gay.asoji.innerpastels.datagen

import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import java.util.*

/**
 * Quick way to use model templates that don't exist in vanilla like `template_spawn_egg`, using strings alone or with texture slots
 */
object CustomModelTemplates {

    // TODO: KDocs later, not in the mood to do them at the moment
    fun createCustomBlockModel(modID: String, string: String): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modID, "block/$string")), Optional.empty())
    }

    fun createCustomBlockModel(modID: String, string: String, textureSlots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modID, "block/$string")), Optional.empty(), textureSlots)
    }

    fun createCustomBlockModel(modID: String, string: String, suffix: String, textureSlots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modID, "block/$string")), Optional.of(suffix), textureSlots)
    }

    // maybe have `template_spawn_egg` or some shit for the string belong be a string enum? idk lol
    fun createCustomItemModel(modID: String, string: String): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modID, "item/$string")), Optional.empty())
    }

    fun createCustomItemModel(modID: String, string: String, textureSlots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modID, "item/$string")), Optional.empty(), textureSlots)
    }

    fun createCustomItemModel(modID: String, string: String, suffix: String, textureSlots: TextureSlot): ModelTemplate {
        return ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modID, "item/$string")), Optional.of(suffix), textureSlots)
    }
}