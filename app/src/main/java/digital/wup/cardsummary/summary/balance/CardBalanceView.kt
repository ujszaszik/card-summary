package digital.wup.cardsummary.summary.balance

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.databinding.LayoutCardBalanceBinding
import digital.wup.cardsummary.extensions.*
import digital.wup.cardsummary.model.CardModel

class CardBalanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutCardBalanceBinding =
        LayoutCardBalanceBinding.inflate(LayoutInflater.from(context), this, true)

    private val animator = CardBalanceAnimator(binding.availableBalanceView)

    private var currentBalance = zero()
    private var availableBalance = zero()
    private var balanceSum = zero()
    private var previousAvailableBalanceWidth = zero()

    fun visualizeAvailableBalance(card: CardModel) {
        currentBalance = card.currentBalance
        availableBalance = card.availableBalance
        balanceSum = currentBalance + availableBalance
        setAvailableBalanceText()
        getAdjustedWidth().run {
            animator.animate(previousAvailableBalanceWidth, this)
            previousAvailableBalanceWidth = this
        }
    }

    private fun setAvailableBalanceText() {
        binding.balanceValue.text = availableBalance.toFormattedString()
    }

    private fun getAdjustedWidth(): Double {
        var ratio = availableBalance / balanceSum
        if (balanceSum.isZero()) ratio.setToZero()
        ratio = ratio.adjustToLimit(one())
        return binding.sumBalanceView.width * ratio
    }

}