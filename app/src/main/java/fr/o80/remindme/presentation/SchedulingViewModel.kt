package fr.o80.remindme.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.o80.remindme.domain.UpdateSchedulesUseCase
import fr.o80.remindme.domain.data.ScheduleRepository
import fr.o80.remindme.domain.toTimeFormat

class SchedulingViewModel @ViewModelInject constructor(
    private val updateSchedules: UpdateSchedulesUseCase,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun onCreate() {
        val (hours, minutes) = scheduleRepository.get()

        _state.value = State(
            hours = hours.toTimeFormat(),
            minutes = minutes.toTimeFormat()
        )
    }

    fun onUpdateSchedulesClicked(hours: String, minutes: String) {
        updateSchedules(hours.toInt(), minutes.toInt())
    }

    data class State(
        val hours: String,
        val minutes: String
    )

}