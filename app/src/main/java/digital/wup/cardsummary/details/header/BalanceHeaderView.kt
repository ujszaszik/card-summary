package digital.wup.cardsummary.details.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.databinding.LayoutBalanceHeaderBinding
import digital.wup.cardsummary.model.CardModel

class BalanceHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutBalanceHeaderBinding =
        LayoutBalanceHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    fun bindCard(card: CardModel) {
        binding.cardModel = card
    }

}