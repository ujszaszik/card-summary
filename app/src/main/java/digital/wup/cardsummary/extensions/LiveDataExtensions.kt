package digital.wup.cardsummary.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeNotNull(activity: AppCompatActivity, block: (T) -> Unit) {
    observe(activity) { it?.let { block.invoke(it) } }
}

fun <T> LiveData<T>.observeNotNull(fragment: Fragment, block: (T) -> Unit) {
    observe(fragment.viewLifecycleOwner) { it?.let { block.invoke(it) } }
}