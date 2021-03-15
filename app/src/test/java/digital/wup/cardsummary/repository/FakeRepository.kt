package digital.wup.cardsummary.repository

import digital.wup.cardsummary.model.CardModel
import io.reactivex.rxjava3.core.Single

class FakeRepository(
    var throwError: Boolean = false,
    var throwErrorType: Throwable = RuntimeException(),
    var testCards: List<CardModel> = listOf(),
    var delay: Boolean = false,
    private var isUnableToFetch: Boolean = false
) : Repository {

    override fun getCards(): Single<List<CardModel>> {
        return if (throwError) Single.error(throwErrorType)
        else Single.just(testCards)
    }

    override fun isUnableToFetch(): Boolean = isUnableToFetch
}