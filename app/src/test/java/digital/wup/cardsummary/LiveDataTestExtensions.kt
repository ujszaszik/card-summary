package digital.wup.cardsummary

import androidx.lifecycle.LiveData
import com.jraska.livedata.test

fun <T> LiveData<T>.testIfEquals(other: T) {
    test().assertHasValue().assertValue(other)
}