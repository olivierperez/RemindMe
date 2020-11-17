package fr.o80.remindme.presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.o80.remindme.R
import fr.o80.remindme.domain.MORNING_REMINDER_CHANNELID
import fr.o80.remindme.domain.MORNING_REMINDER_ID
import fr.o80.remindme.domain.PopupNotificationUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val shouldGoToWork = ShouldGoToWorkUseCase()
    private val popupNotification = PopupNotificationUseCase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.showNotification).setOnClickListener {
            if (shouldGoToWork(Calendar.getInstance())) {
                popupNotification(
                    notificationId = MORNING_REMINDER_ID,
                    message = R.string.goToWork_message,
                    smallIcon = R.drawable.ic_workplace,
                    channelId = MORNING_REMINDER_CHANNELID,
                    channelName = R.string.morningReminder_channel
                )
            } else {
                popupNotification(
                    notificationId = MORNING_REMINDER_ID,
                    message = R.string.stayAtHome_message,
                    smallIcon = R.drawable.ic_home,
                    channelId = MORNING_REMINDER_CHANNELID,
                    channelName = R.string.morningReminder_channel
                )
            }

        }

    }

}
