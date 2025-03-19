package gay.asoji.innerpastels.capes

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import gay.asoji.innerpastels.InnerPastels
import gay.asoji.innerpastels.config.Config
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.minecraft.resources.ResourceLocation
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import java.util.concurrent.CompletableFuture

enum class CapeUtils {
    INSTANCE;

    private val registeredDevs: Multimap<UUID, CapeStyle> = HashMultimap.create()

    private var initialized = false

    fun init() {
        if (initialized) return
        initialized = true
        refresh()
    }

    private fun refresh() {
        CompletableFuture.runAsync {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder(URI.create(URL))
                .GET()
                .build()
            try {
                val body = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
                val users: List<User> = Json.decodeFromString(body)

                registeredDevs.clear()
                users.forEach { u ->
                    u.capes.forEach { c ->
                        registeredDevs.put(UUID.fromString(u.uuid), c)
                    }
                }
            } catch (e: Exception) {
                InnerPastels.LOGGER.error("Failed to fetch cape data", e)
            }
        }
    }

    fun getDevCape(id: UUID): CapeStyle? {
        if (registeredDevs.containsKey(id)) {
            val capes = registeredDevs.get(id)
            capes.forEach { s ->
                if (Config.INSTANCE.get().cape_style == s)
                    return s
            }
            
            return capes.first()
        }

        return null
    }

    companion object {
        private const val URL = "https://raw.githubusercontent.com/asoji/CapeData/refs/heads/main/capes.json"
    }

    @Serializable
    data class User(
        val username: String,
        val uuid: String,
        val reason: String,
        val capes: List<CapeStyle>
    )
    
    @Serializable
    enum class CapeStyle {
        INNER,
        SOFTER,
        DESOLATED;
        
        val location: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(InnerPastels.MOD_ID, "textures/misc/${name.lowercase(Locale.ROOT)}.png")
    }
}
