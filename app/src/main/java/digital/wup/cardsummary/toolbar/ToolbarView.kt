package digital.wup.cardsummary.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.R
import digital.wup.cardsummary.databinding.LayoutToolbarBinding
import digital.wup.cardsummary.extensions.gone
import digital.wup.cardsummary.extensions.setGravity
import digital.wup.cardsummary.extensions.show

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutToolbarBinding =
        LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    private val centerMargin = context.resources.getInteger(R.integer.toolbar_view_center_margin)
    private val startMargin = context.resources.getInteger(R.integer.toolbar_view_start_margin)

    fun setArrowListener(block: () -> Unit) {
        binding.navigationArrow.setOnClickListener { block.invoke() }
    }

    fun handleCurrentState(toolbarState: ToolbarState) {
        setCurrentTitleText(toolbarState)
        when (toolbarState) {
            ToolbarState.OVERVIEW -> doOnOverViewState()
            ToolbarState.DETAILS -> doOnDetailsState()
        }
    }

    private fun doOnOverViewState() {
        binding.navigationArrow.gone()
        binding.toolbarTitleText.setGravity(Gravity.CENTER, centerMargin)
    }

    private fun doOnDetailsState() {
        binding.navigationArrow.show()
        binding.toolbarTitleText.setGravity(Gravity.START, startMargin)
    }

    private fun setCurrentTitleText(state: ToolbarState) {
        binding.toolbarTitleText.text = context.getString(state.titleId)
    }

}