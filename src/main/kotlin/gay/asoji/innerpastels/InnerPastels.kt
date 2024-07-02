package gay.asoji.innerpastels

import gay.asoji.fmw.FMW
import gay.asoji.innerpastels.crab.CrabInTheCode
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * Inner Pastels Internal Library, meant to be used for Softer Pastels, Desolated Pastels, and any other future *Pastel mods.
 * This library adds a lot of common code and helper functions to assist with development for *Pastel mods, especially for Blocks, Items, and Data Generation.
 *
 * @author asojidev
 * @version 1.0.1
 */
object InnerPastels : ModInitializer {
    val MOD_ID = "innerpastels"
    val otherPastelMods = mutableListOf<String>()

    fun registerMods(id: String) {
        otherPastelMods.add(id)
    }

    val LOGGER: Logger = LogManager.getLogger(FMW.getName(MOD_ID))
    override fun onInitialize() {
        CrabInTheCode.crabDeezNuts("oh hello fren!")
    }
}