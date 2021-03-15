package digital.wup.cardsummary.details.account

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import digital.wup.cardsummary.R
import digital.wup.cardsummary.hilt.EmptyHiltActivity
import digital.wup.cardsummary.idling.IdlingResourceRule
import digital.wup.cardsummary.model.AndroidTestCards
import digital.wup.cardsummary.util.DecimalTextFormatter
import digital.wup.cardsummary.util.checkMatchesText
import digital.wup.cardsummary.util.hilt.launchViewInHiltContainer
import digital.wup.cardsummary.util.onView
import digital.wup.cardsummary.util.onVisibleViewOfParent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AccountDetailViewTest {

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
    fun testAccountDetailsView_whenViewIsShown_thenAccountDetailsTitleIsShownAccurately() {
        launchViewInHiltContainer<AccountDetailView> { bindCard(testCard) }

        onView(R.id.accountDetailsTitleText)
            .checkMatchesText(context.getString(R.string.label_text_account_details))
    }

    @Test
    fun testAccountDetailsView_whenViewIsShown_thenAccountLimitLabelIsShownAccurately() {
        launchViewInHiltContainer<AccountDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.accountLimitRow)
            .checkMatchesText(context.getString(R.string.label_text_account_limit))
    }

    @Test
    fun testAccountDetailsView_whenCardIsBound_thenAccountLimitIsShownAccurately() {
        launchViewInHiltContainer<AccountDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.accountLimitRow)
            .checkMatchesText(DecimalTextFormatter.format(testCard.accountDetails.accountLimit))
    }

    @Test
    fun testAccountDetailsView_whenCardIsBound_thenAccountCurrencyIsShownAccurately() {
        launchViewInHiltContainer<AccountDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.currencyText, R.id.accountLimitRow)
            .checkMatchesText(testCard.currency)
    }

    @Test
    fun testAccountDetailsView_whenViewIsShown_thenAccountNumberLabelIsShownAccurately() {
        launchViewInHiltContainer<AccountDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.accountNumberRow)
            .checkMatchesText(context.getString(R.string.label_text_account_number))
    }

    @Test
    fun testAccountDetailsView_whenCardIsBound_thenAccountNumberIsShownAccurately() {
        launchViewInHiltContainer<AccountDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.accountNumberRow)
            .checkMatchesText(testCard.accountDetails.accountNumber)
    }

}