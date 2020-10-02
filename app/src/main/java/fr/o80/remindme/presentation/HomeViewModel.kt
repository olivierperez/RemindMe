package fr.o80.remindme.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.o80.remindme.R
import fr.o80.remindme.domain.MORNING_REMINDER_CHANNELID
import fr.o80.remindme.domain.MORNING_REMINDER_ID
import fr.o80.remindme.domain.PopupNotificationUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import java.util.Calendar

class HomeViewModel(
    private val shouldGoToWork: ShouldGoToWorkUseCase,
    private val popupNotification: PopupNotificationUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    fun onCreate() {
        if (shouldGoToWork(Calendar.getInstance())) {
            _state.value = State(
                icon = R.drawable.ic_workplace
            )
        } else {
            _state.value = State(
                icon = R.drawable.ic_home
            )
        }
    }

    fun onButtonClicked() {
        if (shouldGoToWork(Calendar.getInstance())) {
            popupNotification(
                notificationId = MORNING_REMINDER_ID,
                message = R.string.goToWork_message,
                smallIcon = R.drawable.ic_workplace,
                channelId = MORNING_REMINDER_CHANNELID,
                channelName = R.string.morningReminder_channel
            )
        } else {
            popupNotification(
                notificationId = MORNING_REMINDER_ID,
                message = R.string.stayAtHome_message,
                smallIcon = R.drawable.ic_home,
                channelId = MORNING_REMINDER_CHANNELID,
                channelName = R.string.morningReminder_channel
            )
        }
    }

    data class State(
        @DrawableRes
        val icon: Int
    )

}