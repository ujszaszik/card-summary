package digital.wup.cardsummary.details.maincard

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel

@BindingAdapter("app:mainCardDetailCard")
fun MainCardDetailView.mainCardDetailViewCard(card: CardModel?) {
    card?.let { bindCard(card) }
}