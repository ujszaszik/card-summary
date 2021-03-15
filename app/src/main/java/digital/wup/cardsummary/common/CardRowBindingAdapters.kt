package digital.wup.cardsummary.common

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.util.CardNumberTextFormatter
import digital.wup.cardsummary.util.DateTextConverter
import digital.wup.cardsummary.util.DecimalTextFormatter

@BindingAdapter("app:detailRowValue")
fun CardDetailRow.rowValue(value: String?) = value?.let { setValue(it) }

@BindingAdapter("app:detailRowDateValue")
fun CardDetailRow.rowDateValue(value: String?) =
    value?.let { setValue(DateTextConverter.convert(it)) }

@BindingAdapter("app:detailRowCardNumberValue")
fun CardDetailRow.rowCardNumberValue(value: String?) =
    value?.let { setValue(CardNumberTextFormatter.format(it)) }

@BindingAdapter("app:monetaryRowValue")
fun CardMonetaryRow.rowValue(value: Double?) =
    value?.let { setValue(DecimalTextFormatter.format(value)) }

@BindingAdapter("app:currencyValue")
fun CardMonetaryRow.currencyValues(currency: String?) = currency?.let { setCurrency(currency) }