package digital.wup.cardsummary.util

import digital.wup.cardsummary.extensions.second
import digital.wup.cardsummary.extensions.third

object DateTextConverter {

    const val NOT_AVAILABLE = "N/A"
    private const val DELIMITER = "."
    private const val DOT = '.'
    private const val DASH = '-'
    private const val REGEXP = "."
    private const val DATE_TEXT_REGEXP = "([0-9]{4}[-.][0-9]{2}[-.][0-9]{2}[.]?)"

    fun convert(original: String): String {
        if (!original.matches(Regex(DATE_TEXT_REGEXP))) return NOT_AVAILABLE
        return try {
            val splitText = original.replace(DASH, DOT).split(REGEXP)
            if (splitText.size < 3) return NOT_AVAILABLE
            val year = splitText.first()
            val month = splitText.second()
            val day = splitText.third()
            buildText(day, month, year)
        } catch (e: Exception) {
            NOT_AVAILABLE
        }
    }

    private fun buildText(day: String, month: String, year: String): String {
        return StringBuilder().apply {
            append(day)
            append(DELIMITER)
            append(month)
            append(DELIMITER)
            append(year)
        }.toString()
    }
}