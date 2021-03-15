package digital.wup.cardsummary.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import digital.wup.cardsummary.R
import digital.wup.cardsummary.databinding.LayoutCardMonetaryRowBinding

class CardMonetaryRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), CardRow {

    private val binding: LayoutCardMonetaryRowBinding =
        LayoutCardMonetaryRowBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardMonetaryRow)
        binding.labelText.text = typedArray.getString(R.styleable.CardMonetaryRow_monetaryLabel)
        typedArray.recycle()
    }

    override fun setValue(value: String) {
        binding.valueText.text = value
    }

    fun setCurrency(currency: String) {
        binding.currencyText.text = currency
    }
}