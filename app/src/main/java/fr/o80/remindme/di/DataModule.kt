package fr.o80.remindme.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import fr.o80.remindme.data.SharedPrefScheduleRepository
import fr.o80.remindme.domain.data.ScheduleRepository

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindScheduleRepository(implementation: SharedPrefScheduleRepository): ScheduleRepository
}
