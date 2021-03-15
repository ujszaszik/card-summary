package digital.wup.cardsummary.extensions

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.showPrevious() {
    currentItem -= 1

}

fun ViewPager2.showNext() {
    currentItem += 1
}

fun ViewPager2.setWidth(newHeight: Int?) {
    newHeight?.let {
        val params = layoutParams
        params.height = newHeight
        layoutParams = params
    }
}