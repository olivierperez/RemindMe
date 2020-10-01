package fr.o80.remindme.presentation

import androidx.annotation.DrawableRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.o80.remindme.R
import fr.o80.remindme.domain.ScheduleRemindersUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import fr.o80.remindme.domain.UpdateSchedulesUseCase
import fr.o80.remindme.domain.data.ScheduleRepository
import fr.o80.remindme.domain.toTimeFormat
import java.util.Calendar

class SchedulingViewModel @ViewModelInject constructor(
    private val shouldGoToWork: ShouldGoToWorkUseCase,
    private val scheduleReminders: ScheduleRemindersUseCase,
    private val updateSchedules: UpdateSchedulesUseCase,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun onCreate() {
        val (hours, minutes) = scheduleRepository.get()

        if (shouldGoToWork(Calendar.getInstance())) {
            _state.value = State(
                hours = hours.toTimeFormat(),
                minutes =  minutes.toTimeFormat(),
                icon = R.drawable.ic_workplace
            )
        } else {
            _state.value = State(
                hours = hours.toTimeFormat(),
                minutes =  minutes.toTimeFormat(),
                icon = R.drawable.ic_home
            )
        }

        scheduleReminders(hours, minutes)
    }

    fun onUpdateSchedulesClicked(hours: String, minutes: String) {
        updateSchedules(hours.toInt(), minutes.toInt())
    }

    data class State(
        val hours: String,
        val minutes: String,
        @DrawableRes
        val icon: Int
    )

}