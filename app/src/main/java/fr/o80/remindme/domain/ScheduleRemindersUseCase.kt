package fr.o80.remindme.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.o80.remindme.service.MorningReminderReceiver
import java.util.Calendar
import javax.inject.Inject

class ScheduleRemindersUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    private val alarmManager: AlarmManager
        get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    operator fun invoke(hours: Int, minutes: Int) {
        Log.d("ScheduleReminders", "I'm scheduling the thingy at $hours:$minutes")

        val triggeringTime = Calendar.getInstance().apply {
            val currentHour = get(Calendar.HOUR_OF_DAY)
            val currentMinute = get(Calendar.MINUTE)
            if (currentHour > hours || currentHour == hours && currentMinute > minutes) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
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