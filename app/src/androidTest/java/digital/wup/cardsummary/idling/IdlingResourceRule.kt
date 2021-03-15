package digital.wup.cardsummary.idling

import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class IdlingResourceRule<T : AppCompatActivity>(
    activityTestRule: ActivityTestRule<T>
) : TestWatcher() {

    private val dataBindingIdlingResource = DataBindingIdlingResource(activityTestRule)

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        super.finished(description)
    }

}