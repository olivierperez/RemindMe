package fr.o80.remindme.presentation

import android.os.Looper
import fr.o80.remindme.R
import fr.o80.remindme.domain.MORNING_REMINDER_CHANNELID
import fr.o80.remindme.domain.MORNING_REMINDER_ID
import fr.o80.remindme.domain.PopupNotificationUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@DisplayName("Home screen ViewModel")
internal class HomeViewModelTest {

    @MockK
    lateinit var shouldGoToWorkUseCase: ShouldGoToWorkUseCase

    @MockK(relaxed = true)
    lateinit var popupNotificationUseCase: PopupNotificationUseCase

    @InjectMockKs
    lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setup() {
        mockkStatic(Looper::class)
        every { Looper.getMainLooper() } returns mockk(relaxed = true) {
            every { thread } returns Thread.currentThread()
        }
    }

    @Nested
    @DisplayName("When it's a day at work place")
    inner class GoToWork {

        @BeforeEach
        fun stayAtHome() {
            every { shouldGoToWorkUseCase.invoke(any()) } returns true
        }

        @Test
        @DisplayName("Show the right icon")
        fun shouldGoToWork() {
            viewModel.onCreate()

            assertEquals(R.drawable.ic_workplace, viewModel.state.value?.icon)
        }

        @Test
        @DisplayName("Popup the notification")
        fun shouldNotifyGoToWork() {
            viewModel.onButtonClicked()

            verify {
                popupNotificationUseCase.invoke(
                    notificationId = MORNING_REMINDER_ID,
                    message = R.string.goToWork_message,
                    smallIcon = R.drawable.ic_workplace,
                    channelId = MORNING_REMINDER_CHANNELID,
                    channelName = R.string.morningReminder_channel
                )
            }
        }
    }

    @Nested
    @DisplayName("When it's a couch day")
    inner class StayAtHome {

        @BeforeEach
        fun stayAtHome() {
            every { shouldGoToWorkUseCase.invoke(any()) } returns false
        }

        @Test
        @DisplayName("Show the right icon")
        fun shouldStayAtHome() {
            viewModel.onCreate()

            assertEquals(R.drawable.ic_home, viewModel.state.value?.icon)
        }

        @Test
        @DisplayName("Popup the notification")
        fun shouldNotifyStayAtHome() {
            viewModel.onButtonClicked()

            verify {
                popupNotificationUseCase.invoke(
                    notificationId = MORNING_REMINDER_ID,
                    message = R.string.stayAtHome_message,
                    smallIcon = R.drawable.ic_home,
                    channelId = MORNING_REMINDER_CHANNELID,
                    channelName = R.string.morningReminder_channel
                )
            }
        }
    }
}