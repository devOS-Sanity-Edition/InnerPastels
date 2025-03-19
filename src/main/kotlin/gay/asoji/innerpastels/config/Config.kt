package gay.asoji.innerpastels.config

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import com.mojang.serialization.codecs.RecordCodecBuilder
import gay.asoji.innerpastels.InnerPastels
import gay.asoji.innerpastels.capes.CapeUtils
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

enum class Config {
    INSTANCE;
    
    private val configFile: Path = FabricLoader.getInstance().configDir.resolve("inner_pastels.json")
    private val config: ConfigData = ConfigData.CODEC.decode(JsonOps.INSTANCE, Gson().fromJson(configFile.readText(), JsonElement::class.java))
        .resultOrPartial { InnerPastels.LOGGER.error(it) }
        .orElse(Pair(ConfigData(null), null)).first;

    init {
        if (config.capeStyle != null)
            save()
    }
    
    fun get(): ConfigData {
        return config
    }
    
    fun save() {
        configFile.writeText(ConfigData.CODEC.encodeStart(JsonOps.INSTANCE, config).resultOrPartial { InnerPastels.LOGGER.error(it) }.orElseThrow().asString)
    }
    
    data class ConfigData(
        val capeStyle: CapeUtils.CapeStyle?
    ) {
        companion object {
            val CODEC: Codec<ConfigData> = RecordCodecBuilder.create { it.group(
                    CapeUtils.CapeStyle.CODEC.fieldOf("cape_style").forGetter(ConfigData::capeStyle)
                ).apply(it, ::ConfigData)
            }
        }
    }
}
