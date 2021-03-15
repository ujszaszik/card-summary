package digital.wup.cardsummary.util.hilt

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions
import digital.wup.cardsummary.R
import digital.wup.cardsummary.details.maincard.MainCardDetailView
import digital.wup.cardsummary.hilt.EmptyHiltActivity

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.Theme_AppCompat,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: Fragment.() -> Unit = {}
) {
    val startActivityIntent = startEmptyActivity(themeResId)

    ActivityScenario.launch<EmptyHiltActivity>(startActivityIntent).onActivity { activity ->
        fragmentFactory?.let {
            activity.supportFragmentManager.fragmentFactory = it
        }
        val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )

        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        fragment.action()
    }
}

inline fun <reified T : View> launchViewInHiltContainer(
    @StyleRes themeResId: Int = R.style.Theme_AppCompat,
    crossinline viewAction: T.() -> Unit
) {
    val startActivityIntent = startEmptyActivity(themeResId)

    ActivityScenario.launch<EmptyHiltActivity>(startActivityIntent).onActivity { activity ->
        val constructor = T::class.java.getConstructor(Context::class.java)

        val view: T = constructor.newInstance(activity.applicationContext)
        println(view is MainCardDetailView)
        activity.setContentView(view)
        viewAction.invoke(view)
    }
}

fun startEmptyActivity(themeResId: Int): Intent {
    val key = "androidx.fragment.app.testing.FragmentScenario" +
            ".EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"
    return Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            EmptyHiltActivity::class.java
        )
    ).putExtra(key, themeResId)
}