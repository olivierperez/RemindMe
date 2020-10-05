# RemindMe

An app developed while streaming at [gnu_coding_cafe](https://twitch.tv/gnu_coding_cafe), this was just a pretext to share what an Android developer can be brought to during his day job.
I streamed this during 2 hours per day, 5 consecutive days. You may find the day results in the Pull Requests:

# [Day 1](https://github.com/olivierperez/RemindMe/pull/1)

## Unit testing

- Install / Config JUnit5
  - Jupiter API + Runner
- Test use case

## Use case

- `PopupNotificationUseCase`: Class to show an Android notification
- `ShouldGoToWorkUseCase`: Check if the given date is a day I should go to work place

## Activity

- [Will change in another PR] Call use cases directly from MainActivity

# [Day 2](https://github.com/olivierperez/RemindMe/pull/2)

## Unit testing

- Install MockK
- Write tests for home screen

## Use cases

- `ScheduleRemindersUseCase`: Schedule the reminders at 8AM

## ViewModel

- Move some code from Activity to `HomeViewModel`
	- and rename MainActivity -> `HomeActivity`

## Broadcasters

- `AtStartupReceiver`: Used, at phone startup, schedule reminders
- `MorningReminderReceiver`: When triggered, show the right notification

# [Day 3](https://github.com/olivierperez/RemindMe/pull/3)

## Dependency injection

- Install and use Dagger-hilt
  - Inject things everywhere (Activity, ViewModel, UseCases, BroadcastReceiver)

## Use case

- `UpdateSchedulesUseCase`: Partially implemented (look the next PR)

## View

- Add some fields to the layout
- Send the field values to ViewModel

# [Day 4](https://github.com/olivierperez/RemindMe/pull/4)

## Storage

- `SharedPrefScheduleRepository: ScheduleRepository`: store scheduling preferences into SharedPreferences
	- and read the preferences in order to display the scheuling form

## Dependency injection

- Inject `SharedPrefScheduleRepository` thanks to a Dagger-`@Module`

## Fix

- `ScheduleRemindersUseCase`: schedule the FIRST occurence to be in the future, not in the past

## View

- Format the hour and the time on 2 characters
- Move code from Activity to `SchedulingFragment`
- Make the icon color daynight-dependent

## Theme

- Configure architecture of themes

# [Day 5](https://github.com/olivierperez/RemindMe/pull/5)

## View

- Split application into 2 screens (2 **Fragments** in the same Activity)
- Install **NavigationComponent** to navigate between theses 2 screens
