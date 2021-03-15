package digital.wup.cardsummary.repository

import digital.wup.cardsummary.model.CardModel
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getCards(): Single<List<CardModel>>

    fun isUnableToFetch(): Boolean
}