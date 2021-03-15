package digital.wup.cardsummary.details

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import digital.wup.cardsummary.R
import digital.wup.cardsummary.hilt.EmptyHiltActivity
import digital.wup.cardsummary.idling.IdlingResourceRule
import digital.wup.cardsummary.model.AndroidTestCards
import digital.wup.cardsummary.util.checkViewIsDisplayWhenScrolledTo
import digital.wup.cardsummary.util.checkViewIsDisplayed
import digital.wup.cardsummary.util.hilt.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityTestRule<EmptyHiltActivity> =
        ActivityTestRule(EmptyHiltActivity::class.java)

    @get:Rule
    val dataBindingTestRule = IdlingResourceRule(activityRule)

    private val testBundle = AndroidTestCards.testBundle

    private val testBundleWithMalformedValues = AndroidTestCards.testBundleWithMalformedValues

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenBalanceHeaderViewIsShown() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.balanceHeaderView)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenBalanceOverviewLayoutIsShown() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.balanceOverviewTitleText)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenAccountDetailViewIsShown() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.accountDetailView)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenMainCardDetailViewIsShown() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.mainCardDetailView)
    }

    @Test
    fun testDetailsFragment_whenViewIsLoaded_thenSupplementaryCardDetailViewIsShown() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayWhenScrolledTo(R.id.supplementaryCardDetailView)
    }


    @Test
    fun testBalanceHeaderView_whenCardIsBound_thenRightBalanceLayoutIsDisplayed() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.rightBalanceLayout)
    }

    @Test
    fun testBalanceHeaderView_whenCardIsBound_thenLeftBalanceLayoutIsDisplayed() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.leftBalanceLayout)
    }

    @Test
    fun testBalanceHeaderView_whenCardIsBound_thenAvailableBalanceChartIsDisplayed() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.availableBalanceChartView)
    }

    @Test
    fun testBalanceHeaderView_whenCardIsBound_thenCurrentBalanceChartIsDisplayed() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.currentBalanceChartView)
    }

    @Test
    fun testBalanceHeaderView_whenCardWithBiggerCurrentBalanceThanAvailableBalanceIsBound_thenUnderPendingChartIsDisplayed() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundle)

        checkViewIsDisplayed(R.id.underPendingChartView)
    }

    @Test
    fun testBalanceHeaderView_whenCardWithBiggerAvailableBalanceThanCurrentBalanceIsBound_thenOverPendingChartIsDisplayed() {
        launchFragmentInHiltContainer<DetailsFragment>(testBundleWithMalformedValues)

        checkViewIsDisplayed(R.id.overPendingChartView)
    }


}