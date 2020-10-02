package fr.o80.remindme.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TimeTest {

    @Test
    fun shouldFormatTime() {
        assertEquals("08", 8.toTimeFormat())
        assertEquals("00", 0.toTimeFormat())
        assertEquals("09", 9.toTimeFormat())
    }

    @Test
    fun shouldNotFormatTime() {
        assertEquals("10", 10.toTimeFormat())
        assertEquals("11", 11.toTimeFormat())
        assertEquals("12", 12.toTimeFormat())
    }

}