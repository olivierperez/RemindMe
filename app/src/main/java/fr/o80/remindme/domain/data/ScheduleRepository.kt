package fr.o80.remindme.domain.data

interface ScheduleRepository {
    fun save(hours: Int, minutes: Int)
    fun get(): SchedulingTime
}
