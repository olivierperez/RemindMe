package fr.o80.remindme.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.remindme.R
import kotlinx.android.synthetic.main.fragment_information.*

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private val viewModel by viewModels<InformationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::bindState)
        viewModel.onCreate()
    }

    private fun bindState(state: InformationViewModel.State) {
        goToSomewhereIcon.setImageResource(state.icon)
        information.setText(state.information)

        configurationButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(InformationFragmentDirections.actionConfigure())
        )
    }
}