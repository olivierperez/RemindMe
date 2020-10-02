package fr.o80.remindme.domain

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PopupNotificationUseCase @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }

    operator fun invoke(notificationId: Int, @StringRes message: Int, @DrawableRes smallIcon: Int, channelId: String, @StringRes channelName: Int) {
        createChannel(channelId, channelName)
        showNotification(channelId, notificationId, message, smallIcon)
    }

    private fun showNotification(channelId: String, notificationId: Int, @StringRes message: Int, smallIcon: Int) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(context.getString(message))
            .setSmallIcon(smallIcon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
            .build()

        notificationManager.notify(
            notificationId,
            notification
        )
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel(channelId: String, @StringRes channelName: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            channelId,
            context.getString(channelName),
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
    }

}