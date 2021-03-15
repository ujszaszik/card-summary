package digital.wup.cardsummary.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import digital.wup.cardsummary.databinding.FragmentDetailsBinding
import digital.wup.cardsummary.extensions.setToolbarStateTo
import digital.wup.cardsummary.toolbar.ToolbarState

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.cardModel = args.cardModel
        setToolbarStateTo(ToolbarState.DETAILS)
        return binding.root
    }

}