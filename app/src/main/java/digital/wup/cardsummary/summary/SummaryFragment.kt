package digital.wup.cardsummary.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import digital.wup.cardsummary.CardsViewModel
import digital.wup.cardsummary.databinding.FragmentSummaryBinding
import digital.wup.cardsummary.extensions.observeNotNull
import digital.wup.cardsummary.extensions.setToolbarStateTo
import digital.wup.cardsummary.model.CardResult
import digital.wup.cardsummary.toolbar.ToolbarState

@AndroidEntryPoint
class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private val viewModel by viewModels<CardsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(layoutInflater)
        setToolbarStateTo(ToolbarState.OVERVIEW)
        observeCards()
        observeCurrentCard()
        setDetailsButtonListener()
        return binding.root
    }

    private fun observeCards() {
        viewModel.cardResult.observeNotNull(this) {
            if (it is CardResult.Success) {
                binding.cardSlider.initSlider(it.cards, binding.tabLayout) { pageNo ->
                    viewModel.onPageSelected(pageNo)
                }
            }
        }
    }

    private fun observeCurrentCard() {
        viewModel.currentCard.observe(viewLifecycleOwner) {
            binding.cardModel = it
        }
    }

    private fun setDetailsButtonListener() {
        binding.detailsButton.setOnClickListener {
            SummaryFragmentDirections.actionSummaryFragmentToDetailsFragment(binding.cardModel!!)
                .run {
                    findNavController().navigate(this)
                }
        }
    }

}