package fr.o80.remindme.presentation

import android.os.Looper
import fr.o80.remindme.R
import fr.o80.remindme.domain.ScheduleRemindersUseCase
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
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
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@DisplayName("Home screen ViewModel")
internal class HomeViewModelTest {

    @MockK
    lateinit var shouldGoToWorkUseCase: ShouldGoToWorkUseCase

    @MockK(relaxed = true)
    lateinit var scheduleRemindersUseCase: ScheduleRemindersUseCase

    @MockK(relaxed = true)
    lateinit var updateSchedules: UpdateSchedulesUseCase

    @MockK(relaxed = true)
    lateinit var scheduleRepository: ScheduleRepository

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
        @DisplayName("Schedule reminders")
        fun shouldScheduleReminders() {
            every { scheduleRepository.get() } returns SchedulingTime(7, 0)

            viewModel.onCreate()

            verify { scheduleRemindersUseCase.invoke(7, 0) }
        }

        @Test
        @DisplayName("Show the right icon")
        fun shouldGoToWork() {
            viewModel.onCreate()

            assertEquals(R.drawable.ic_workplace, viewModel.state.value?.icon)
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

    @Nested
    @DisplayName("When it's a couch day")
    inner class StayAtHome {

        @BeforeEach
        fun stayAtHome() {
            every { shouldGoToWorkUseCase.invoke(any()) } returns false
        }

        @Test
        @DisplayName("Schedule reminders")
        fun shouldScheduleReminders() {
            every { scheduleRepository.get() } returns SchedulingTime(12, 30)

            viewModel.onCreate()

            verify { scheduleRemindersUseCase.invoke(12, 30) }
        }

        @Test
        @DisplayName("Show the right icon")
        fun shouldStayAtHome() {
            viewModel.onCreate()

            assertEquals(R.drawable.ic_home, viewModel.state.value?.icon)
        }

        @Test
        @DisplayName("Update the schedules")
        fun shouldUpdateSchedules() {
            viewModel.onUpdateSchedulesClicked("77", "66")

            verify {
                updateSchedules.invoke(77, 66)
            }
        }
    }
}