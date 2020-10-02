package fr.o80.remindme.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.o80.remindme.R
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import java.util.Calendar

class InformationViewModel @ViewModelInject constructor(
    private val shouldGoToWork: ShouldGoToWorkUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun onCreate() {
        if (shouldGoToWork(Calendar.getInstance())) {
            _state.value = State(
                icon = R.drawable.ic_workplace,
                information = R.string.goToWork_message
            )
        } else {
            _state.value = State(
                icon = R.drawable.ic_home,
                information = R.string.stayAtHome_message
            )
        }
    }

    data class State(
        @DrawableRes
        val icon: Int,
        @StringRes
        val information: Int
    )

}