package digital.wup.cardsummary.repository

import digital.wup.cardsummary.extensions.takeElements
import digital.wup.cardsummary.extensions.toSingleOrError
import digital.wup.cardsummary.model.CardModel
import digital.wup.cardsummary.network.CardService
import digital.wup.cardsummary.network.NetworkUtil
import digital.wup.cardsummary.repository.PaperModule.Companion.CARD_BOOK_NAME
import io.paperdb.Book
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CardsRepository @Inject constructor(
    private val cardsService: CardService,
    private val cardsBook: Book
) : Repository {

    override fun getCards(): Single<List<CardModel>> {
        return getCardsFromDatabase()
            .onErrorResumeNext {
                getCardsFromNetwork()
                    .doAfterSuccess { saveCardsToDatabase(it) }
            }
    }

    private fun saveCardsToDatabase(cards: List<CardModel>) {
        cardsBook.write(CARD_BOOK_NAME, cards)
    }

    private fun getCardsFromDatabase(): Single<List<CardModel>> {
        return cardsBook.read<List<CardModel>>(CARD_BOOK_NAME).toSingleOrError()
    }

    private fun getCardsFromNetwork(): Single<List<CardModel>> {
        return cardsService.getCards().takeElements(3).singleOrError()
    }

    override fun isUnableToFetch(): Boolean {
        return !NetworkUtil.isConnected && cardsBook.read<List<CardModel>>(CARD_BOOK_NAME).isEmpty()
    }

}