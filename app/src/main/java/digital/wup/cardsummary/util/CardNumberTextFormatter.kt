package digital.wup.cardsummary.util

import digital.wup.cardsummary.extensions.concat
import digital.wup.cardsummary.extensions.replaceIfIndexIsLowerThen

object CardNumberTextFormatter {

    const val NOT_AVAILABLE = "N/A"
    private const val DELIMITER = "-"
    private const val STAR = "*"
    private const val NUMBER_REGEXP = "[\\d.]"
    private const val CARD_NUMBER_REGEXP = "([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})"

    fun format(cardNumber: String): String {
        if (!cardNumber.matches(Regex(CARD_NUMBER_REGEXP))) return NOT_AVAILABLE
        val splitCardNumber = cardNumber.split(DELIMITER)
        return try {
            splitCardNumber.replaceIfIndexIsLowerThen(3) {
                it.replace((NUMBER_REGEXP).toRegex(), STAR)
            }.concat(DELIMITER)
        } catch (e: Exception) {
            NOT_AVAILABLE
        }
    }
}