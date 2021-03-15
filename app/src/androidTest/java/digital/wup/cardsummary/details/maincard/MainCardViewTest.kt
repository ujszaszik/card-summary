package digital.wup.cardsummary.details.maincard

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
class MainCardViewTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityTestRule<EmptyHiltActivity> =
        ActivityTestRule(EmptyHiltActivity::class.java)

    @get:Rule
    val dataBindingTestRule = IdlingResourceRule(activityRule)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext


    private val testCard = AndroidTestCards.testCardOne

    private val testCardWithWrongCardNumberFormat =
        AndroidTestCards.testCardWithMalformedValues

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testMainCardView_whenViewIsShown_thenMainCardTitleIsShownAccurately() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCard) }

        onView(R.id.mainCardTitleText)
            .checkMatchesText(context.getString(R.string.label_text_main_card))
    }

    @Test
    fun testMainCardView_whenViewIsShown_thenCardHolderNameRowIsDisplayed() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCard) }

        checkViewIsDisplayed(R.id.mainCardHolderName)
    }

    @Test
    fun testMainCardView_whenViewIsShown_thenCardHolderLabelShowsAccurately() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.mainCardHolderName)
            .checkMatchesText(context.getString(R.string.label_text_cardholder_name))
    }

    @Test
    fun testMainCardView_whenCardIsBound_thenCardHolderNameShowsAccurately() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.mainCardHolderName)
            .checkMatchesText(testCard.cardHolderName)
    }

    @Test
    fun testMainCardView_whenViewIsShown_thenCardNumberLabelShowsAccurately() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.labelText, R.id.mainCardNumber)
            .checkMatchesText(context.getString(R.string.label_text_card_number))
    }

    @Test
    fun testMainCardView_whenCardIsBound_thenCardNumberShowsAccurately() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.mainCardNumber)
            .checkMatchesText(CardNumberTextFormatter.format(testCard.cardNumber))
    }

    @Test
    fun testMainCardView_whenCardIsBoundAndInvalidFormatProvided_thenCardNumberNotAvailableIsShownAccurately() {
        launchViewInHiltContainer<MainCardDetailView> { bindCard(testCardWithWrongCardNumberFormat) }

        onVisibleViewOfParent(R.id.valueText, R.id.mainCardNumber)
            .checkMatchesText(CardNumberTextFormatter.format(testCardWithWrongCardNumberFormat.cardNumber))
    }
}