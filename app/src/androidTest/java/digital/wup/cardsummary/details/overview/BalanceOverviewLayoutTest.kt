package digital.wup.cardsummary.details.overview

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
class BalanceOverviewLayoutTest {

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
    fun testBalanceOverviewLayout_WhenViewIsShown_thenBalanceOverviewTitleIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onView(R.id.balanceOverviewTitleText)
            .checkMatchesText(context.getString(R.string.label_text_balance_overview))
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenBalanceCarriedLabelIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.balanceCarriedRow)
            .checkMatchesText(context.getString(R.string.label_text_balance_carried))
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenBalanceCarriedValueIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.balanceCarriedRow)
            .checkMatchesText(DecimalTextFormatter.format(testCard.balanceCarriedOverFromLastStatement))
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenBalanceCarriedCurrencyIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.currencyText, R.id.balanceCarriedRow)
            .checkMatchesText(testCard.currency)
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenSpendingSinceLastStatementLabelIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.spendingSinceLastStatementRow)
            .checkMatchesText(context.getString(R.string.label_text_total_spendings))
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenSpendingSinceLastStatementValueIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.spendingSinceLastStatementRow)
            .checkMatchesText(DecimalTextFormatter.format(testCard.spendingsSinceLastStatement))
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenSpendingSinceLastStatementCurrencyIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.currencyText, R.id.spendingSinceLastStatementRow)
            .checkMatchesText(testCard.currency)
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenYourLastRePaymentLabelIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.lastRepayment)
            .checkMatchesText(context.getString(R.string.label_text_last_repayment))
    }

    @Test
    fun testBalanceOverviewLayout_whenViewIsShown_thenYourLastRePaymentValueIsShownAccurately() {
        launchViewInHiltContainer<BalanceOverviewLayout> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.lastRepayment)
            .checkMatchesText(DateTextConverter.convert(testCard.yourLastRepayment))
    }

}