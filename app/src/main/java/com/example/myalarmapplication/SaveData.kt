package com.example.myalarmapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class SaveData {
    var context : Context? = null
    constructor(context: Context){
        this.context = context
    }

    fun setAlarm(hours: Int, minutes: Int){
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        calendar.set(Calendar.SECOND, 0)


        //creating the intent to definy as broadcast receiver
        var intent = Intent(context, MyBroadcastReceiver::class.java)
        intent.putExtra("message", "Alarm Time")
        intent.action = "com.ggonzales.alarmsetting"

        //token to give to another application
        val pendingIntent = PendingIntent.getBroadcast(this.context, 7, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        Log.d("Sending Broadcast", intent!!.action)
        val alarmMan = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmMan.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY, pendingIntent )

        alarmMan.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent)

    }
}