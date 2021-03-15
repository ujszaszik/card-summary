package digital.wup.cardsummary.extensions

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import digital.wup.cardsummary.hilt.HiltCardsActivity
import digital.wup.cardsummary.toolbar.ToolbarState

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.hide() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.setWidth(value: Int) {
    val params = layoutParams
    params.width = value
    layoutParams = params
}

fun TextView.setGravity(gravity: Int, startMargin: Int = 0) {
    val textViewLayoutParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
    )
    textViewLayoutParams.gravity = gravity
    textViewLayoutParams.marginStart = startMargin
    layoutParams = textViewLayoutParams
}

fun Fragment.setToolbarStateTo(state: ToolbarState) {
    val activity = activity as HiltCardsActivity
    activity.handleToolbarStateChange(state)
}