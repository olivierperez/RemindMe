package fr.o80.remindme.domain

import java.util.Calendar
import javax.inject.Inject

class ShouldGoToWorkUseCase @Inject constructor() {

    operator fun invoke(calendar: Calendar): Boolean {
        val isEvenDay = calendar.get(Calendar.DAY_OF_YEAR) % 2 == 0
        val isFriday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
        return isEvenDay || isFriday
    }

}
