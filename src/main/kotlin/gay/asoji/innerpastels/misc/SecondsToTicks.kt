package gay.asoji.innerpastels.misc

/**
 * Multiplies your [Int] by the game's 20 ticks per second, making something like 5s * 20tps = 100t. Useful for anything related to using game ticks but in whole seconds.
 *
 * Example:
 * ```kotlin
 * val seconds: Int = 5
 * val totalSecondsInTicks: Int = seconds.secondsToTicks() // Returns 100
 * ```
 *
 * @return [Int]
 */
fun Int.secondsToTicks() = this * 20