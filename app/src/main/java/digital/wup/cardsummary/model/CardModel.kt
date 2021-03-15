package digital.wup.cardsummary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class CardModel(
    var cardId: String,
    var issuer: String,
    var cardNumber: String,
    var expirationDate: String,
    var cardHolderName: String,
    var friendlyName: String,
    var currency: String,
    var cvv: String,
    var availableBalance: Double,
    var currentBalance: Double,
    var minPayment: Double,
    var dueDate: String,
    var reservations: Double,
    var balanceCarriedOverFromLastStatement: Double,
    var spendingsSinceLastStatement: Double,
    var yourLastRepayment: String,
    var accountDetails: AccountDetails,
    var status: CardStatus,
    var cardImage: String
) : Parcelable