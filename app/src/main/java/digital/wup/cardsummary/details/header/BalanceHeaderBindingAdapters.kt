package digital.wup.cardsummary.details.header

import android.widget.TextView
import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel
import digital.wup.cardsummary.util.DecimalTextFormatter

@BindingAdapter("app:balanceChartCard")
fun BalanceChartView.balanceChartCard(card: CardModel?) {
    card?.let { drawBalanceChart(it) }
}

@BindingAdapter("app:balanceHeaderCard")
fun BalanceHeaderView.balanceHeaderCard(card: CardModel?) {
    card?.let { bindCard(it) }
}

@BindingAdapter("app:balanceHeaderDecimal")
fun TextView.balanceHeaderDecimal(value: Double?) {
    value?.let { text = DecimalTextFormatter.format(value) }
}
