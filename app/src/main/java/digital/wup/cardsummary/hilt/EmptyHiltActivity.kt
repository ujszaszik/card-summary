package digital.wup.cardsummary.hilt

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import digital.wup.cardsummary.toolbar.ToolbarState

@AndroidEntryPoint
class EmptyHiltActivity : AppCompatActivity(), HiltCardsActivity {

    private var currentToolbarState: ToolbarState? = null

    override fun handleToolbarStateChange(state: ToolbarState) {
        currentToolbarState = state
    }
}