package fr.o80.remindme.service

import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.remindme.di.HiltBroadcastReceiver
import fr.o80.remindme.domain.ScheduleRemindersUseCase
import javax.inject.Inject

@AndroidEntryPoint
class AtStartupReceiver : HiltBroadcastReceiver() {

    @Inject
    lateinit var scheduleReminders: ScheduleRemindersUseCase

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "android.intent.action.BOOT_COMPLETED") return

        super.onReceive(context, intent)

        scheduleReminders()
    }

}
