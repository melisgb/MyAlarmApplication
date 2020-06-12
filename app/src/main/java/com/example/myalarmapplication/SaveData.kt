package com.example.myalarmapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import java.util.*

class SaveData {
    var context : Context? = null
    var sharedRef : SharedPreferences? = null //to get current time
    constructor(context: Context){
        this.context = context
        this.sharedRef = context.getSharedPreferences("myRef",  Context.MODE_PRIVATE)
    }
    fun saveXMLFile(hour: Int, minute: Int){
        //This will save xml file with the alarm time previous, so it will not be discarded.
        var editor = sharedRef!!.edit()
        editor.putInt("hour", hour)
        editor.putInt("minute", minute)
        editor.commit()
    }
    fun getHour():Int {
        return sharedRef!!.getInt("hour", 0)
    }
    fun getMinute():Int {
        return sharedRef!!.getInt("minute", 0)
    }


    fun setAlarm(){
        val hours = getHour()
        val minutes = getHour()
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
        alarmMan
            .setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, AlarmManager.INTERVAL_DAY, pendingIntent )

    }
}