package gay.asoji.innerpastels.misc

/**
 * Translation string to show which candy shows how much hunger.
 * [actual strings in library's /src/main/resources/assets/innerpastels/lang/en_us.json]
 */
enum class CandyTranslationString(val candyHungerString: String) {
    /**
     * Translation string for Taffy's description
     */
    TAFFY("item.innerpastels.candies.hunger.description.taffy"),

    /**
     * Translation string for Cotton Candy's description
     */
    COTTON("item.innerpastels.candies.hunger.description.cotton"),

    /**
     * Translation string for Hard Candy's description
     */
    HARD("item.innerpastels.candies.hunger.description.hard")
}