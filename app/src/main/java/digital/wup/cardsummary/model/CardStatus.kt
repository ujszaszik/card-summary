package digital.wup.cardsummary.model

import kotlinx.serialization.Serializable

@Serializable
enum class CardStatus {
    ACTIVE, BLOCKED
}