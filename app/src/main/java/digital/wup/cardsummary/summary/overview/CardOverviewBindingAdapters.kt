package digital.wup.cardsummary.summary.overview

import androidx.databinding.BindingAdapter
import digital.wup.cardsummary.model.CardModel


@BindingAdapter("app:cardModel")
fun CardOverviewLayout.cardModel(card: CardModel?) = card?.let { bindCard(it) }