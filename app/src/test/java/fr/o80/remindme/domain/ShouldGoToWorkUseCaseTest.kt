package fr.o80.remindme.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

internal class ShouldGoToWorkUseCaseTest {

    private val shouldGoToWork = ShouldGoToWorkUseCase()

    @DisplayName("Should I stay at home?")
    @ParameterizedTest(name = "2020-{1}-{0} -> {2}")
    @MethodSource("days")
    fun shouldStayAtHome(dayOfMonth: Int, month: Int, result: Boolean) {
        // When
        val shouldStayAtHome = shouldGoToWork(makeDayOf2020(dayOfMonth, month))

        // Then
        assertEquals(result, shouldStayAtHome)
    }

    companion object {
        @JvmStatic
        fun days(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(28, Calendar.SEPTEMBER, true),
                Arguments.of(29, Calendar.SEPTEMBER, false),
                Arguments.of(30, Calendar.SEPTEMBER, true),
                Arguments.of(1, Calendar.OCTOBER, false),
                Arguments.of(2, Calendar.OCTOBER, true),

                Arguments.of(5, Calendar.OCTOBER, false),
                Arguments.of(6, Calendar.OCTOBER, true),
                Arguments.of(7, Calendar.OCTOBER, false),
                Arguments.of(8, Calendar.OCTOBER, true),
                Arguments.of(9, Calendar.OCTOBER, true)
            )
        }

        private fun makeDayOf2020(dayOfMonth: Int, month: Int): Calendar {
            return Calendar.getInstance().apply {
                set(Calendar.YEAR, 2020)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
        }
    }

}
