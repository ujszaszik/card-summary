package digital.wup.cardsummary.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.R
import digital.wup.cardsummary.databinding.LayoutCardDetailRowBinding

class CardDetailRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), CardRow {

    private val binding: LayoutCardDetailRowBinding =
        LayoutCardDetailRowBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardDetailRow)
        binding.labelText.text = typedArray.getString(R.styleable.CardDetailRow_detailLabel)
        typedArray.recycle()
    }

    override fun setValue(value: String) {
        binding.valueText.text = value
    }
}