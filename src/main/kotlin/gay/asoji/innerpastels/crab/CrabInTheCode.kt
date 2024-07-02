package gay.asoji.innerpastels.crab

import com.mojang.blaze3d.Blaze3D
import gay.asoji.innerpastels.InnerPastels
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

/**
 * This is an extremely vital class. This class is crucial for the correct functionality of
 * Inner Pastels. This is extremely important, and cannot be removed under any circumstances.
 * This class single-handedly holds together the Library. Without this class, everything
 * falls apart. This class is the reason humanity has not yet gone extinct. This class is
 * the only thing holding together the remains of the time-space continuum. Without this incredibly
 * crucial class, everything ever will fail catastrophically. In the event this class is removed,
 * expect you to go next, followed by the rest of humanity, and finally, all reality.
 * Once again, DO NOT REMOVE THIS CLASS.
 */
object CrabInTheCode {
    /**
     * *pinch*
     * Make sure you always give it a string! Or else!
     */
    @Environment(EnvType.CLIENT)
    fun crabDeezNuts(randomShit: String) {
        fun theCrabMadeYouLoseTheFuckingGameYouFuckingAsshole() {
            // don't... don't hurt the crab, and i wont crash your JVM. it's just that simple.
            InnerPastels.LOGGER.error("Uh oh! Someone fed the Crab an empty string! You have now lost the game!")
            return Blaze3D.youJustLostTheGame()
        }
        if (randomShit.isEmpty() || randomShit.isBlank() || randomShit == null) {
            theCrabMadeYouLoseTheFuckingGameYouFuckingAsshole()
        }
    }
}