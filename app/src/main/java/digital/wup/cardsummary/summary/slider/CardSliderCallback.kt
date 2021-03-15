package digital.wup.cardsummary.summary.slider

import androidx.viewpager2.widget.ViewPager2

class CardSliderCallback(
    private val onPageSelected: (Int) -> Unit,
    private val onMinimumPosition: () -> Unit,
    private val onMaximumPosition: () -> Unit,
    private val onInterPosition: () -> Unit,
    private val size: Int
) : ViewPager2.OnPageChangeCallback() {

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        when (position) {
            0 -> onMinimumPosition.invoke()
            size - 1 -> onMaximumPosition.invoke()
            else -> onInterPosition.invoke()
        }
    }

    override fun onPageSelected(position: Int) {
        onPageSelected.invoke(position)
    }
}