package fr.o80.remindme.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.o80.remindme.R
import fr.o80.remindme.domain.MORNING_REMINDER_CHANNELID
import fr.o80.remindme.domain.MORNING_REMINDER_ID
import fr.o80.remindme.domain.PopupNotificationUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import java.util.Calendar

class MorningReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val shouldGoToWork = ShouldGoToWorkUseCase()
        val popupNotification = PopupNotificationUseCase(context)

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
