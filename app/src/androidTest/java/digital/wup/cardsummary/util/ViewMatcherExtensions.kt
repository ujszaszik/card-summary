package digital.wup.cardsummary.util

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

fun onView(viewId: Int): ViewInteraction {
    return onView(withId(viewId))
}

fun ViewInteraction.checkMatchesText(text: String): ViewInteraction {
    return check(matches(withText(text)))
}

fun checkViewIsDisplayed(viewId: Int): ViewInteraction {
    return onView(withId(viewId)).check(matches(isDisplayed()))
}

fun checkViewIsNotDisplayed(viewId: Int): ViewInteraction {
    return onView(withId(viewId)).check(matches(not(isDisplayed())))
}

fun checkViewIsDisplayWhenScrolledTo(viewId: Int): ViewInteraction {
    return onView(viewId).perform(scrollTo()).check(matches(isDisplayed()))
}

fun onVisibleViewOfParent(viewId: Int, descendantId: Int): ViewInteraction {
    return onView(
        allOf(
            withId(viewId),
            isDescendantOfA(withId(descendantId)),
            withEffectiveVisibility(Visibility.VISIBLE)
        )
    )
}