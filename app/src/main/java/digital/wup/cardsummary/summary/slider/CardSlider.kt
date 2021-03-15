package digital.wup.cardsummary.summary.slider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import digital.wup.cardsummary.databinding.LayoutCardSliderBinding
import digital.wup.cardsummary.extensions.*
import digital.wup.cardsummary.model.CardModel

class CardSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutCardSliderBinding =
        LayoutCardSliderBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var adapter: CardSliderAdapter

    fun initSlider(list: List<CardModel>, tabLayout: TabLayout, onPageSelected: (Int) -> Unit) {
        initViewPager(list, tabLayout, onPageSelected)
        setArrowListeners()
    }

    private fun initViewPager(
        list: List<CardModel>,
        tabLayout: TabLayout,
        onPageSelected: (Int) -> Unit
    ) {
        adapter = CardSliderAdapter(context, list) { binding.viewPager.setWidth(it) }
        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(getCallback(list.size, onPageSelected))
        connectWithTabLayout(tabLayout)
    }

    private fun connectWithTabLayout(tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, binding.viewPager) { _, _ ->
        }.attach()
    }

    private fun getCallback(
        size: Int,
        onPageSelected: (Int) -> Unit
    ): ViewPager2.OnPageChangeCallback {
        return CardSliderCallback(
            onPageSelected = onPageSelected,
            onMinimumPosition = { onMinimumPosition() },
            onMaximumPosition = { onMaximumPosition() },
            onInterPosition = { onInterPosition() },
            size = size
        )
    }

    private fun onMinimumPosition() {
        binding.arrowBack.hide()
    }

    private fun onMaximumPosition() {
        binding.arrowRight.hide()
    }

    private fun onInterPosition() {
        binding.arrowBack.show()
        binding.arrowRight.show()
    }

    private fun setArrowListeners() {
        binding.arrowBack.setOnClickListener {
            binding.viewPager.showPrevious()
        }

        binding.arrowRight.setOnClickListener {
            binding.viewPager.showNext()
        }
    }
}