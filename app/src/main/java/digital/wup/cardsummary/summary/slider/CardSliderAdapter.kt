package digital.wup.cardsummary.summary.slider

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import digital.wup.cardsummary.R
import digital.wup.cardsummary.model.CardModel

class CardSliderAdapter(
    private val context: Context,
    private val list: List<CardModel>,
    private val doOnItemHeightMeasured: (Int?) -> Unit
) :
    RecyclerView.Adapter<CardSliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSliderViewHolder {
        return CardSliderViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardSliderViewHolder, position: Int) {
        val cardId = list[position].cardId.toInt()
        val drawableName = getDrawableName(cardId)
        context.getDrawable(drawableName).also {
            holder.bindImage(it)
            doOnItemHeightMeasured.invoke(it?.intrinsicHeight)
        }
    }

    override fun getItemCount() = list.size

    private fun Context.getDrawable(drawableName: String): Drawable? {
        val resourceId = resources.getIdentifier(
            drawableName, getString(R.string.definition_type_drawable), packageName
        )
        return ContextCompat.getDrawable(this, resourceId)
    }

    private fun getDrawableName(id: Int): String {
        return StringBuilder().apply {
            append(context.getString(R.string.card_image_prefix))
            if (id > 1) append(id)
        }.toString()
    }
}