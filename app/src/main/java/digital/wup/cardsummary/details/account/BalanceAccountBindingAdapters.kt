package digital.wup.cardsummary.details.account

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel

@BindingAdapter("app:accountDetailCard")
fun AccountDetailView.accountDetailCard(card: CardModel?) {
    card?.let { bindCard(card) }
}