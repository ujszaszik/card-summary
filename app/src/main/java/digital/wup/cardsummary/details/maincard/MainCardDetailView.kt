package digital.wup.cardsummary.details.maincard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import digital.wup.cardsummary.databinding.LayoutDetailsMainCardBinding
import digital.wup.cardsummary.model.CardModel

class MainCardDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutDetailsMainCardBinding =
        LayoutDetailsMainCardBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindCard(card: CardModel) {
        binding.cardModel = card
    }

}