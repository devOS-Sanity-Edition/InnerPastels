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
import java.util.*
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

object Config {
    private val configFile: Path = FabricLoader.getInstance().configDir.resolve("innerpastels.json")
    private val config: ConfigData = if (configFile.exists()) ConfigData.CODEC.decode(JsonOps.INSTANCE, Gson().fromJson(configFile.readText(), JsonElement::class.java))
        .resultOrPartial { InnerPastels.LOGGER.error(it) }
        .orElse(Pair(ConfigData(null), null)).first;
    else
        ConfigData(null)

    init {
        if (config.capeStyle != null)
            save()
    }

    fun get(): ConfigData {
        return config
    }
    
    fun save() {
        if (!configFile.exists())
            configFile.createFile()

        configFile.writeText(ConfigData.CODEC.encodeStart(JsonOps.INSTANCE, config).resultOrPartial { InnerPastels.LOGGER.error(it) }.orElseThrow().toString())
    }
    
    data class ConfigData(
        var capeStyle: CapeUtils.CapeStyle?
    ) {
        constructor(capeStyle: Optional<CapeUtils.CapeStyle>) : this(capeStyle.orElse(null))

        companion object {
            val CODEC: Codec<ConfigData> = RecordCodecBuilder.create { it.group(
                    CapeUtils.CapeStyle.CODEC.optionalFieldOf("cape_style").forGetter { Optional.ofNullable(it.capeStyle) }
                ).apply(it, ::ConfigData)
            }
        }
    }
}
