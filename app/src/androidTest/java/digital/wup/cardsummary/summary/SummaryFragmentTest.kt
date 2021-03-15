package digital.wup.cardsummary.summary

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import digital.wup.cardsummary.R
import digital.wup.cardsummary.hilt.EmptyHiltActivity
import digital.wup.cardsummary.util.checkViewIsDisplayed
import digital.wup.cardsummary.util.checkViewIsNotDisplayed
import digital.wup.cardsummary.util.hilt.launchFragmentInHiltContainer
import digital.wup.cardsummary.util.onView
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SummaryFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityTestRule<EmptyHiltActivity> =
        ActivityTestRule(EmptyHiltActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenCardSliderIsShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        checkViewIsDisplayed(R.id.cardSlider)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenBackArrowIsShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        checkViewIsNotDisplayed(R.id.arrowBack)
        checkViewIsDisplayed(R.id.arrowRight)
    }

    @Test
    fun testDetailsFragment_whenSwipeRight_thenBothArrowsAreShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        onView(R.id.arrowRight).perform(click())

        checkViewIsDisplayed(R.id.arrowBack)
        checkViewIsDisplayed(R.id.arrowRight)
    }

    @Test
    fun testDetailsFragment_whenSwipeToLastElement_thenOnlyBackArrowIsShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        onView(R.id.arrowRight).perform(click())
        onView(R.id.arrowRight).perform(click())

        checkViewIsDisplayed(R.id.arrowBack)
        checkViewIsNotDisplayed(R.id.arrowRight)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenBalanceViewIsShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        checkViewIsDisplayed(R.id.balanceView)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenCardOverviewLayoutIsShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        checkViewIsDisplayed(R.id.cardOverviewLayout)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenDetailsButtonIsShown() {
        launchFragmentInHiltContainer<SummaryFragment>()

        checkViewIsDisplayed(R.id.detailsButton)
    }

    @Test
    fun testDetailFragment_whenDetailsButtonClicked_thenNavigatesToDetails() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        launchFragmentInHiltContainer<SummaryFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(R.id.detailsButton).perform(scrollTo()).perform(click())

        Assert.assertTrue(navController.currentDestination?.id == R.id.detailsFragment)
    }

}