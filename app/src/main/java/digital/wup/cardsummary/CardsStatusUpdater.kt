package digital.wup.cardsummary

import android.view.View
import digital.wup.cardsummary.extensions.gone
import digital.wup.cardsummary.extensions.show
import digital.wup.cardsummary.model.CardResult
import digital.wup.cardsummary.model.CardResult.*

class CardsStatusUpdater(
    private val cardsLayout: View,
    private val loadingLayout: View,
    private val errorLayout: View,
    private val disconnectedLayout: View
) {


    fun handleResult(status: CardResult) {
        when (status) {
            is Loading -> doOnLoading()
            is Success -> doOnSuccess()
            is Error -> doOnError()
            is Disconnected -> doOnDisconnected()
        }
    }

    private fun doOnLoading() {
        cardsLayout.gone()
        loadingLayout.show()
        errorLayout.gone()
        disconnectedLayout.gone()
    }

    private fun doOnSuccess() {
        cardsLayout.show()
        loadingLayout.gone()
        errorLayout.gone()
        disconnectedLayout.gone()
    }

    private fun doOnError() {
        cardsLayout.gone()
        loadingLayout.gone()
        errorLayout.show()
        disconnectedLayout.gone()
    }

    private fun doOnDisconnected() {
        cardsLayout.gone()
        loadingLayout.gone()
        errorLayout.gone()
        disconnectedLayout.show()
    }

}