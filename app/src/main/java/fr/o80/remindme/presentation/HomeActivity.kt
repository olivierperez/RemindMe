package fr.o80.remindme.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.remindme.R
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.state.observe(this, ::bindState)
        viewModel.onCreate()
    }

    private fun bindState(state: HomeViewModel.State) {
        goToSomewhereIcon.setImageResource(state.icon)

        hours.setText(state.hours)
        minutes.setText(state.minutes)

        updateSchedules.visibility = View.VISIBLE
        updateSchedules.setOnClickListener {
            viewModel.onUpdateSchedulesClicked(hours.text.toString(), minutes.text.toString())
        }
    }

}
