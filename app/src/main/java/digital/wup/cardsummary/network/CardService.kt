package digital.wup.cardsummary.network

import digital.wup.cardsummary.model.CardModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CardService {

    @GET("cards.json")
    fun getCards(): Observable<List<CardModel>>
}