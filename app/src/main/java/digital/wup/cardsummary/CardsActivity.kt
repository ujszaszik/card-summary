package digital.wup.cardsummary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import digital.wup.cardsummary.databinding.ActivityCardsBinding
import digital.wup.cardsummary.extensions.isGraterThanZero
import digital.wup.cardsummary.extensions.observeNotNull
import digital.wup.cardsummary.hilt.HiltCardsActivity
import digital.wup.cardsummary.model.CardResult
import digital.wup.cardsummary.toolbar.ToolbarState


@AndroidEntryPoint
class CardsActivity : AppCompatActivity(), HiltCardsActivity {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private lateinit var binding: ActivityCardsBinding
    private val viewModel by viewModels<CardsViewModel>()

    private lateinit var statusUpdater: CardsStatusUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cards)
        setUpNavHost()
        setupToolbar()
        initStatusUpdater()
        observeCardsForUpdateBadge()
    }

    private fun setUpNavHost() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        if (!isStartingDestination()) super.onBackPressed()
    }

    private fun isStartingDestination(): Boolean {
        return navController.currentDestination?.id == R.id.summaryFragment
    }

    override fun handleToolbarStateChange(state: ToolbarState) {
        binding.toolbarView.handleCurrentState(state)
    }

    private fun setupToolbar() {
        binding.toolbarView.setArrowListener { onSupportNavigateUp() }
    }

    private fun initStatusUpdater() {
        with(binding) {
            statusUpdater = CardsStatusUpdater(
                cardsLayout = navHostFragment,
                loadingLayout = loadingView,
                errorLayout = errorView,
                disconnectedLayout = disconnectedView
            )
        }
    }

    private fun observeCardsForUpdateBadge() {
        viewModel.cardResult.observeNotNull(this) {
            statusUpdater.handleResult(it)
            when (it) {
                is CardResult.Success -> controlBadge(it.cards.size)
                else -> removeBadge()
            }
        }
    }

    private fun controlBadge(number: Int) {
        if (number.isGraterThanZero()) updateBadge(number)
        else removeBadge()
    }

    private fun updateBadge(number: Int) {
        binding.cardsBottomNavigationView
            .getOrCreateBadge(R.id.statementsFragment).number = number
    }

    private fun removeBadge() {
        binding.cardsBottomNavigationView.removeBadge(R.id.statementsFragment)
    }

}