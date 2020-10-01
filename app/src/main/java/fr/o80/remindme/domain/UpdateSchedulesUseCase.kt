package fr.o80.remindme.domain

import fr.o80.remindme.domain.data.ScheduleRepository
import javax.inject.Inject

class UpdateSchedulesUseCase @Inject constructor(
    private val scheduleRemindersUseCase: ScheduleRemindersUseCase,
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(hours: Int, minutes: Int) {
        scheduleRepository.save(hours, minutes)
        scheduleRemindersUseCase(hours, minutes)
    }

}