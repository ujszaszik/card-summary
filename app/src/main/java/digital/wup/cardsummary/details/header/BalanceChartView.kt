package digital.wup.cardsummary.details.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.databinding.LayoutBalanceChartBinding
import digital.wup.cardsummary.extensions.*
import digital.wup.cardsummary.model.CardModel
import kotlin.math.abs

class BalanceChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutBalanceChartBinding =
        LayoutBalanceChartBinding.inflate(LayoutInflater.from(context), this, true)


    private var currentBalance = zero()
    private var currentBalanceRatio = zero()
    private var availableBalance = zero()
    private var availableBalanceRatio = zero()
    private var pendingBalance = zero()
    private var pendingBalanceRatio = zero()

    private var totalWidth = 0

    fun drawBalanceChart(card: CardModel) {
        totalWidth = binding.leftBalanceLayout.width + binding.rightBalanceLayout.width
        currentBalance = card.currentBalance
        availableBalance = card.availableBalance
        pendingBalance = currentBalance - availableBalance
        setAdjustedHeights()
    }

    private fun setAdjustedHeights() {
        when (getPendingType()) {
            BalancePendingType.OVER -> doOnOverPending()
            BalancePendingType.UNDER -> doOnUnderPending()
        }
    }

    private fun getPendingType(): BalancePendingType {
        return if (pendingBalance.isNegative()) BalancePendingType.OVER
        else BalancePendingType.UNDER
    }

    private fun doOnOverPending() {
        currentBalanceRatio = currentBalance.validPercentageOf(availableBalance)
        pendingBalanceRatio = abs(pendingBalance).validPercentageOf(availableBalance)
        with(binding) {
            currentBalanceChartView.setAdjustedWidth(currentBalanceRatio)
            overPendingChartView.setAdjustedWidth(pendingBalanceRatio)
            availableBalanceChartView.setAdjustedWidth(one())
            underPendingChartView.setAdjustedWidth(zero())
        }
    }

    private fun doOnUnderPending() {
        availableBalanceRatio = availableBalance.validPercentageOf(currentBalance)
        pendingBalanceRatio = pendingBalance.validPercentageOf(currentBalance)
        with(binding) {
            currentBalanceChartView.setAdjustedWidth(one())
            overPendingChartView.setAdjustedWidth(zero())
            availableBalanceChartView.setAdjustedWidth(availableBalanceRatio)
            underPendingChartView.setAdjustedWidth(pendingBalanceRatio)
        }
    }

    private fun View.setAdjustedWidth(ratio: Double) {
        val adjustedWidth = totalWidth.half() * ratio
        this.setWidth(adjustedWidth.toInt())
    }

}