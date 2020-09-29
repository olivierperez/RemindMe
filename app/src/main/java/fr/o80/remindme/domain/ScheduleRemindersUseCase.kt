package fr.o80.remindme.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import fr.o80.remindme.service.MorningReminderReceiver
import java.util.Calendar

class ScheduleRemindersUseCase(
    private val context: Context
) {

    private val alarmManager: AlarmManager
        get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    operator fun invoke() {
        val triggeringTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val intent = Intent(context, MorningReminderReceiver::class.java)

        alarmManager.setRepeating(
            AlarmManager.RTC,
            triggeringTime,
            AlarmManager.INTERVAL_DAY,
            PendingIntent.getBroadcast(
                context,
                MORNING_REMINDER_REQUESTCODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }

}