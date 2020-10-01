package fr.o80.remindme.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.remindme.R
import kotlinx.android.synthetic.main.fragment_scheduling.*

@AndroidEntryPoint
class SchedulingFragment : Fragment() {

    private val viewModel by viewModels<SchedulingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scheduling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::bindState)
        viewModel.onCreate()
    }

    private fun bindState(state: SchedulingViewModel.State) {
        goToSomewhereIcon.setImageResource(state.icon)

        hours.setText(state.hours)
        minutes.setText(state.minutes)

        updateSchedules.visibility = View.VISIBLE
        updateSchedules.setOnClickListener {
            viewModel.onUpdateSchedulesClicked(hours.text.toString(), minutes.text.toString())
        }
    }

    companion object {
        fun newInstance(): SchedulingFragment = SchedulingFragment()
    }
}