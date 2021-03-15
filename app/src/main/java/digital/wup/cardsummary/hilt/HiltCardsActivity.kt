package digital.wup.cardsummary.hilt

import digital.wup.cardsummary.toolbar.ToolbarState

interface HiltCardsActivity {

    fun handleToolbarStateChange(state: ToolbarState)
}