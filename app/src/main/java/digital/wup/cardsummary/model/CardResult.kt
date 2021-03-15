package digital.wup.cardsummary.model

sealed class CardResult {

    object Loading : CardResult()

    class Success(val cards: List<CardModel>) : CardResult()

    class Error(val reason: String?) : CardResult()

    object Disconnected : CardResult()
}
