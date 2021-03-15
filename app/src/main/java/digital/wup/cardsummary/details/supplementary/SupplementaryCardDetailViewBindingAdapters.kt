package digital.wup.cardsummary.details.supplementary

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel

@BindingAdapter("app:supplementaryCardDetailCard")
fun SupplementaryCardDetailView.supplementaryCardDetailViewCard(card: CardModel?) {
    card?.let { bindCard(card) }
}