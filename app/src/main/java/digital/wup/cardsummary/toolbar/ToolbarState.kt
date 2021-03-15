package digital.wup.cardsummary.toolbar

import android.os.Parcelable
import digital.wup.cardsummary.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class ToolbarState(val titleId: Int) : Parcelable {
    OVERVIEW(R.string.label_title_premium_card),
    DETAILS(R.string.label_title_details)
}