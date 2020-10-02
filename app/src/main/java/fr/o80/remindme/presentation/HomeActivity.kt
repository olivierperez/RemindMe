package fr.o80.remindme.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import fr.o80.remindme.R
import fr.o80.remindme.domain.PopupNotificationUseCase
import fr.o80.remindme.domain.ScheduleRemindersUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase

class HomeActivity : AppCompatActivity() {

    private val viewModel = HomeViewModel(ShouldGoToWorkUseCase(), PopupNotificationUseCase(this))
    private val scheduleReminders = ScheduleRemindersUseCase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button = findViewById<MaterialButton>(R.id.showNotification)

        viewModel.state.observe(this) { state ->
            button.visibility = View.VISIBLE
            button.icon = ContextCompat.getDrawable(this, state.icon)
            button.setOnClickListener {
                viewModel.onButtonClicked()
            }
        }

        viewModel.onCreate()

        scheduleReminders()
    }

}
