package digital.wup.cardsummary.summary.balance

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel


@BindingAdapter("app:cardModel")
fun CardBalanceView.cardModel(card: CardModel?) {
    card?.let { visualizeAvailableBalance(it) }
}