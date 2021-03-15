package digital.wup.cardsummary.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object DecimalTextFormatter {

    private val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = '\''
    }

    private val decimalFormat = DecimalFormat().apply {
        decimalFormatSymbols = symbols
        groupingSize = 3
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    fun format(number: Double): String = decimalFormat.format(number)
}