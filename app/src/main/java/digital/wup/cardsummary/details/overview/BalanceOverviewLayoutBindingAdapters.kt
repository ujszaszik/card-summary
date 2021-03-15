package digital.wup.cardsummary.details.overview

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel

@BindingAdapter("app:balanceOverviewCard")
fun BalanceOverviewLayout.supplementaryCardDetailViewCard(card: CardModel?) {
    card?.let { bindCard(card) }
}