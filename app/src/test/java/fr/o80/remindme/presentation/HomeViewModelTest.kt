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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
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

    @Test
    fun shouldGoToWork() {
        every { shouldGoToWorkUseCase.invoke(any()) } returns true

        viewModel.onCreate()

        assertEquals(R.drawable.ic_workplace, viewModel.state.value?.icon)
    }

    @Test
    fun shouldNotifyGoToWork() {
        every { shouldGoToWorkUseCase.invoke(any()) } returns true

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

    @Test
    fun shouldStayAtHome() {
        every { shouldGoToWorkUseCase.invoke(any()) } returns false

        viewModel.onCreate()

        assertEquals(R.drawable.ic_home, viewModel.state.value?.icon)
    }

    @Test
    fun shouldNotifyStayAtHome() {
        every { shouldGoToWorkUseCase.invoke(any()) } returns false

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