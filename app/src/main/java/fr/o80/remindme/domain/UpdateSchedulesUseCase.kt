package fr.o80.remindme.domain

import javax.inject.Inject

class UpdateSchedulesUseCase @Inject constructor(
    private val scheduleRemindersUseCase: ScheduleRemindersUseCase
) {
    operator fun invoke(hours: Int, minutes: Int) {
        storeSchedulesTime(hours, minutes)
        scheduleRemindersUseCase(hours, minutes)
    }

    private fun storeSchedulesTime(hours: Int, minutes: Int) {
        // TODO
    }
}