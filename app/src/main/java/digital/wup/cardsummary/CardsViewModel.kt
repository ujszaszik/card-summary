package digital.wup.cardsummary

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import digital.wup.cardsummary.extensions.alsoAddTo
import digital.wup.cardsummary.model.CardModel
import digital.wup.cardsummary.model.CardResult
import digital.wup.cardsummary.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CardsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _cardResult = MutableLiveData<CardResult>()
    val cardResult: LiveData<CardResult> get() = _cardResult

    private var cardList = listOf<CardModel>()

    private val _currentCard = MutableLiveData<CardModel>()
    val currentCard: LiveData<CardModel> get() = _currentCard

    private val compositeDisposable = CompositeDisposable()

    init {
        getCards()
    }

    fun getCards() {
        _cardResult.postValue(CardResult.Loading)
        if (repository.isUnableToFetch()) {
            _cardResult.postValue(CardResult.Disconnected)
            return
        } else {
            fetchCards()
        }
    }

    private fun fetchCards() {
        repository.getCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::updateCards,
                ::sendErrorMessage
            ).alsoAddTo(compositeDisposable)
    }

    private fun updateCards(cards: List<CardModel>) {
        _cardResult.postValue(CardResult.Success(cards))
        cardList = cards
    }

    private fun sendErrorMessage(error: Throwable?) {
        _cardResult.postValue(CardResult.Error(error?.message))
    }

    fun onPageSelected(position: Int) {
        _currentCard.postValue(cardList[position])
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}