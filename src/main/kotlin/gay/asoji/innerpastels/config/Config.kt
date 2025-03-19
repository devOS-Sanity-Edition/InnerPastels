package gay.asoji.innerpastels.config

import gay.asoji.innerpastels.capes.CapeUtils
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

enum class Config {
    INSTANCE;
    
    private val configFile: Path = FabricLoader.getInstance().configDir.resolve("inner_pastels.json")
    private val config: ConfigData = if (configFile.exists()) Json.decodeFromString(configFile.readText()) else ConfigData(null);

    init {
        if (config.cape_style != null)
            save()
    }
    
    fun get(): ConfigData {
        return config
    }
    
    fun save() {
        configFile.writeText(Json.encodeToString(config))
    }
    
    @Serializable
    data class ConfigData(
        @Suppress("PropertyName") val cape_style: CapeUtils.CapeStyle?
    )
}
