package digital.wup.cardsummary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class AccountDetails(
    var accountLimit: Double,
    var accountNumber: String
) : Parcelable