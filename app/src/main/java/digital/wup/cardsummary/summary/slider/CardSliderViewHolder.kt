package digital.wup.cardsummary.summary.slider

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import digital.wup.cardsummary.databinding.LayoutCardImageBinding

class CardSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private lateinit var binding: LayoutCardImageBinding

        fun from(parent: ViewGroup): CardSliderViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            binding = LayoutCardImageBinding.inflate(inflater, parent, false)
            return CardSliderViewHolder(binding.root)
        }
    }

    fun bindImage(drawable: Drawable?) {
        binding.pagerImage.setImageDrawable(drawable)
        binding.executePendingBindings()
    }
}