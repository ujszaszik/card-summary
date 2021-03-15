package digital.wup.cardsummary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import digital.RxSchedulerRule
import digital.wup.cardsummary.model.CardModel
import digital.wup.cardsummary.model.CardResult
import digital.wup.cardsummary.model.TestCards
import digital.wup.cardsummary.repository.FakeRepository
import io.reactivex.rxjava3.subscribers.TestSubscriber
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock


@RunWith(MockitoJUnitRunner::class)
class CardsViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    private lateinit var viewModel: CardsViewModel

    private lateinit var testSubscriber: TestSubscriber<List<CardModel>>

    @Mock
    private lateinit var testCards: List<CardModel>

    var sequential: Lock = ReentrantLock()

    @Before
    fun setup() {
        sequential.lock()
        MockitoAnnotations.initMocks(this)
        testSubscriber = TestSubscriber<List<CardModel>>()
    }

    @After
    fun teardown() {
        sequential.unlock()
    }

    @Test
    fun `when cards are loaded, then status is success`() {
        viewModel = CardsViewModel(FakeRepository())
        viewModel.getCards()
        viewModel.cardResult.test()
        assert(viewModel.cardResult.value is CardResult.Success)
    }

    @Test
    fun `when cards are loaded, then appropriate values are posted to live data`() {
        viewModel = CardsViewModel(FakeRepository(testCards = testCards))
        viewModel.getCards()
        viewModel.cardResult.test()
        val result = viewModel.cardResult.value as CardResult.Success
        assert(result.cards == testCards)
    }

    @Test
    fun `when error occurs, then status is error`() {
        viewModel = CardsViewModel(FakeRepository(true))
        viewModel.getCards()
        viewModel.cardResult.test()
        assert(viewModel.cardResult.value is CardResult.Error)
    }

    @Test
    fun `when error occurs, then appropriate message is posted to live data`() {
        val errorMessage = "Fetch Cards Test Error Message"
        val testException = RuntimeException(errorMessage)
        viewModel = CardsViewModel(FakeRepository(true, testException))
        viewModel.getCards()
        viewModel.cardResult.test()
        val result = viewModel.cardResult.value as CardResult.Error
        assert(result.reason == errorMessage)
    }

    @Test
    fun `when network is not connected, then status is disconnected`() {
        viewModel = CardsViewModel(FakeRepository(isUnableToFetch = true))
        viewModel.getCards()
        viewModel.cardResult.test()
        assert(viewModel.cardResult.value is CardResult.Disconnected)
    }

    @Test
    fun `given #onPageSelected called, when value is passed, live data is updated with appropriate value`() {
        val position = 0
        viewModel = CardsViewModel(FakeRepository(testCards = TestCards.testCardsList))
        viewModel.getCards()
        viewModel.cardResult.test()
        viewModel.onPageSelected(position)
        viewModel.currentCard.testIfEquals(TestCards.testCardsList[position])
    }
}