package com.example.myalarmapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

//This receiver needs  to be registered on manifest. It will receive the phone event
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //Original method
        var bundle = intent!!.extras
        val msg = bundle!!.getString("message")
        Log.d("Receiving Broadcast", intent!!.action)

        if(intent!!.action.equals("com.ggonzales.alarmsetting")){
            Toast.makeText(context!!.applicationContext, msg, Toast.LENGTH_SHORT).show()
            val notifyMe =  NotificationHelper(context)
            notifyMe.notify(1,
                notifyMe!!.getNotification("First", "Alarm set", "first"))

        }
        else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){
            //if the phone restarted, set again the alarm previously requested
            val saveData = SaveData(context!!.applicationContext)
            saveData.setAlarm()
        }
    }

}