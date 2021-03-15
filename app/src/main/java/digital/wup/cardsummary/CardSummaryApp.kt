package digital.wup.cardsummary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import digital.wup.cardsummary.network.NetworkUtil
import io.paperdb.Paper
import javax.inject.Inject

@HiltAndroidApp
class CardSummaryApp : Application() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate() {
        super.onCreate()
        Paper.init(applicationContext)
        networkUtil.registerNetworkCallback()
    }

}