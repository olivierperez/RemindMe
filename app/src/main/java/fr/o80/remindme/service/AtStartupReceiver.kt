package fr.o80.remindme.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.o80.remindme.domain.ScheduleRemindersUseCase

class AtStartupReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "android.intent.action.BOOT_COMPLETED") return

        val scheduleReminders = ScheduleRemindersUseCase(context)
        scheduleReminders()
    }

}
