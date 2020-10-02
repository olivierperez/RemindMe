package fr.o80.remindme.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.o80.remindme.domain.DEFAULT_SCHEDULING_HOURS
import fr.o80.remindme.domain.DEFAULT_SCHEDULING_MINUTES
import fr.o80.remindme.domain.SHAREDPREF_REMINDER
import fr.o80.remindme.domain.data.ScheduleRepository
import fr.o80.remindme.domain.data.SchedulingTime
import javax.inject.Inject

private const val HOURS = "HOURS"
private const val MINUTES = "MINUTES"

class SharedPrefScheduleRepository @Inject constructor(
    @ApplicationContext
    private val context: Context
) : ScheduleRepository {

    private val sharedPref: SharedPreferences
        get() = context.getSharedPreferences(SHAREDPREF_REMINDER, Context.MODE_PRIVATE)

    override fun save(hours: Int, minutes: Int) {
        sharedPref.edit {
            putInt(HOURS, hours)
            putInt(MINUTES, minutes)
        }
    }

    override fun get(): SchedulingTime {
        return SchedulingTime(
            sharedPref.getInt(HOURS, DEFAULT_SCHEDULING_HOURS),
            sharedPref.getInt(MINUTES, DEFAULT_SCHEDULING_MINUTES)
        )
    }

}
