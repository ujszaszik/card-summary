package digital.wup.cardsummary.details.header

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import digital.wup.cardsummary.R
import digital.wup.cardsummary.hilt.EmptyHiltActivity
import digital.wup.cardsummary.idling.IdlingResourceRule
import digital.wup.cardsummary.model.AndroidTestCards
import digital.wup.cardsummary.util.*
import digital.wup.cardsummary.util.hilt.launchViewInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class BalanceHeaderViewTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityTestRule<EmptyHiltActivity> =
        ActivityTestRule(EmptyHiltActivity::class.java)

    @get:Rule
    val dataBindingTestRule = IdlingResourceRule(activityRule)

    private val testCard = AndroidTestCards.testCardOne

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testBalanceHeaderView_whenViewIsShown_thenAvailableBalanceLabelIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onView(R.id.availableLabel)
            .checkMatchesText(context.getString(R.string.label_text_available))
    }

    @Test
    fun testAccountDetailsView_whenViewIsShown_thenAvailableBalanceValueIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onView(R.id.availableBalanceValue)
            .checkMatchesText(DecimalTextFormatter.format(testCard.availableBalance))
    }

    @Test
    fun testBalanceHeaderView_whenViewIsShown_thenCurrentBalanceLabelIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onView(R.id.currentBalanceLabel)
            .checkMatchesText(context.getString(R.string.label_text_current_balance))
    }

    @Test
    fun testBalanceHeaderView_whenViewIsShown_thenCurrentBalanceValueIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onView(R.id.currentBalanceValue)
            .checkMatchesText(DecimalTextFormatter.format(testCard.currentBalance))
    }

    @Test
    fun testAccountDetailsView_whenViewIsShown_thenReservationsRowLabelIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.reservationsRow)
            .checkMatchesText(context.getString(R.string.label_text_reservations))
    }

    @Test
    fun testBalanceHeaderView_whenCardIsBound_thenReservationValueIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.reservationsRow)
            .checkMatchesText(DecimalTextFormatter.format(testCard.reservations))
    }

    @Test
    fun testBalanceHeaderView_whenCardIsBound_thenReservationCurrencyIsShownAccurately() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.currencyText, R.id.reservationsRow)
            .checkMatchesText(testCard.currency)
    }

    @Test
    fun testBalanceHeaderView_whenViewIsShown_thenReservationMarkerLayoutIsDisplayed() {
        launchViewInHiltContainer<BalanceHeaderView> { bindCard(testCard) }

        checkViewIsDisplayed(R.id.reservationMarkerLayout)
    }

}