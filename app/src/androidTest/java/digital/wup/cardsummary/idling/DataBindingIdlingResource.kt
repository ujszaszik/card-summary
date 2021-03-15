package digital.wup.cardsummary.idling

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.rule.ActivityTestRule

class DataBindingIdlingResource(
    private val activityTestRule: ActivityTestRule<*>
) : IdlingResource {
    private lateinit var resourceCallback: ResourceCallback
    private var wasNotIdle = false

    override fun getName(): String {
        return IdlingResource::class.java.name
    }

    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    override fun isIdleNow(): Boolean {
        val idle = !getBindings().any { it.hasPendingBindings() }
        if (idle) {
            wasNotIdle = false
        } else {
            wasNotIdle = true
            activityTestRule.activity.findViewById<View>(android.R.id.content).postDelayed({
                isIdleNow
            }, 16)
        }
        return idle
    }

    private fun getBindings(): List<ViewDataBinding> {
        return (activityTestRule.activity as? FragmentActivity)
            ?.supportFragmentManager
            ?.fragments
            ?.mapNotNull {
                it.view?.let { view ->
                    DataBindingUtil.getBinding<ViewDataBinding>(
                        view
                    )
                }
            } ?: emptyList()
    }
}