package digital.wup.cardsummary.summary.balance

import android.animation.ValueAnimator
import android.view.View
import digital.wup.cardsummary.R
import digital.wup.cardsummary.extensions.setWidth

class CardBalanceAnimator(private val view: View) {

    fun animate(startWidth: Double, endWidth: Double) {
        ValueAnimator.ofFloat(startWidth.toFloat(), endWidth.toFloat()).run {
            duration = getAnimationTime()
            addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                view.setWidth(value.toInt())
            }
            start()
        }
    }

    private fun getAnimationTime(): Long {
        return view.context.resources.getInteger(R.integer.chart_animation_time).toLong()
    }
}