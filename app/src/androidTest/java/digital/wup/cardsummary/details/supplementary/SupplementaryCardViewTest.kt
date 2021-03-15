package digital.wup.cardsummary.details.supplementary

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import digital.wup.cardsummary.R
import digital.wup.cardsummary.hilt.EmptyHiltActivity
import digital.wup.cardsummary.idling.IdlingResourceRule
import digital.wup.cardsummary.model.AndroidTestCards
import digital.wup.cardsummary.util.CardNumberTextFormatter
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
class SupplementaryCardViewTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityTestRule<EmptyHiltActivity> =
        ActivityTestRule(EmptyHiltActivity::class.java)

    @get:Rule
    val dataBindingTestRule = IdlingResourceRule(activityRule)

    private val testCard = AndroidTestCards.testCardOne

    private val testCardWithWrongCardNumberFormat =
        AndroidTestCards.testCardWithMalformedValues

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testSupplementaryCardView_whenViewIsShown_thenSupplementaryCardTitleIsShownAccurately() {
        launchViewInHiltContainer<SupplementaryCardDetailView> { bindCard(testCard) }

        onView(R.id.supplementaryCardTitleText)
            .checkMatchesText(context.getString(R.string.label_text_supplementary_card))
    }

    @Test
    fun testSupplementaryCardView_whenCardIsBound_thenCardHolderNameShowsAccurately() {
        launchViewInHiltContainer<SupplementaryCardDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.supplementaryCardHolderName)
            .checkMatchesText(testCard.cardHolderName)
    }

    @Test
    fun testSupplementaryCardView_whenCardIsBound_thenCardNumberShowsAccurately() {
        launchViewInHiltContainer<SupplementaryCardDetailView> { bindCard(testCard) }

        onVisibleViewOfParent(R.id.valueText, R.id.supplementaryCardNumber)
            .checkMatchesText(CardNumberTextFormatter.format(testCard.cardNumber))
    }

    @Test
    fun testSupplementaryCardView_whenCardIsBoundAndInvalidFormatProvided_thenCardNumberNotAvailableIsShownAccurately() {
        launchViewInHiltContainer<SupplementaryCardDetailView> {
            bindCard(testCardWithWrongCardNumberFormat)
        }

        onVisibleViewOfParent(R.id.valueText, R.id.supplementaryCardNumber)
            .checkMatchesText(CardNumberTextFormatter.format(testCardWithWrongCardNumberFormat.cardNumber))
    }
}