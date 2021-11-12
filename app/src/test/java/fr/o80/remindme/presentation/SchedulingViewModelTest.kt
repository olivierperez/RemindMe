package fr.o80.remindme.presentation

import android.os.Looper
import fr.o80.remindme.domain.ScheduleRemindersUseCase
import fr.o80.remindme.domain.UpdateSchedulesUseCase
import fr.o80.remindme.domain.data.ScheduleRepository
import fr.o80.remindme.domain.data.SchedulingTime
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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@DisplayName("Home screen ViewModel")
internal class SchedulingViewModelTest {

    @MockK(relaxed = true)
    lateinit var scheduleRemindersUseCase: ScheduleRemindersUseCase

    @MockK(relaxed = true)
    lateinit var updateSchedules: UpdateSchedulesUseCase

    @MockK(relaxed = true)
    lateinit var scheduleRepository: ScheduleRepository

    @InjectMockKs
    lateinit var viewModel: SchedulingViewModel

    @BeforeEach
    fun setup() {
        mockkStatic(Looper::class)
        every { Looper.getMainLooper() } returns mockk(relaxed = true) {
            every { thread } returns Thread.currentThread()
        }
    }

    @Test
    @DisplayName("Show the saved schedules")
    fun shouldShowSchedules() {
        every { scheduleRepository.get() } returns SchedulingTime(1, 2)

        viewModel.onCreate()

        assertEquals(SchedulingViewModel.State("01", "02"), viewModel.state.value)
    }

    @Test
    @DisplayName("Update the schedules")
    fun shouldUpdateSchedules() {
        viewModel.onUpdateSchedulesClicked("99", "88")

        verify {
            updateSchedules.invoke(99, 88)
        }
    }
}