package digital.wup.cardsummary.details.supplementary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.databinding.LayoutDetailsSupplementaryCardBinding
import digital.wup.cardsummary.model.CardModel

class SupplementaryCardDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutDetailsSupplementaryCardBinding =
        LayoutDetailsSupplementaryCardBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindCard(card: CardModel) {
        binding.cardModel = card
    }

}