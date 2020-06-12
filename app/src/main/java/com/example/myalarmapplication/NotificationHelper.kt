package com.example.myalarmapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color

class NotificationHelper(context: Context) : ContextWrapper(context) {
    val notManager : NotificationManager by lazy{
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    init{
        val channel1 = NotificationChannel(FIRST_CHANNEL, "First channel", NotificationManager.IMPORTANCE_DEFAULT)
        channel1.lightColor = Color.GREEN
        channel1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        notManager.createNotificationChannel(channel1)

    }

    fun getNotification(title : String, body: String, channelID: String) : Notification.Builder {
        return Notification.Builder(applicationContext, channelID )
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColor(getColor(R.color.colorPrimaryDark))
            .setAutoCancel(true)
    }

    fun notify(id: Int, notification: Notification.Builder){
        notManager.notify(id, notification.build())
    }

    companion object {
        val FIRST_CHANNEL = "first"
    }

}