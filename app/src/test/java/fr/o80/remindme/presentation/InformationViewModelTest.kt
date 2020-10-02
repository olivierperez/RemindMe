package fr.o80.remindme.presentation

import android.os.Looper
import fr.o80.remindme.R
import fr.o80.remindme.domain.ShouldGoToWorkUseCase
import fr.o80.remindme.domain.data.SchedulingTime
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@DisplayName("Home screen ViewModel")
internal class InformationViewModelTest {

    @MockK
    lateinit var shouldGoToWorkUseCase: ShouldGoToWorkUseCase

    @InjectMockKs
    lateinit var viewModel: InformationViewModel

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

            Assertions.assertEquals(R.drawable.ic_workplace, viewModel.state.value?.icon)
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
        fun shouldGoToWork() {
            viewModel.onCreate()

            Assertions.assertEquals(R.drawable.ic_home, viewModel.state.value?.icon)
        }
    }
}