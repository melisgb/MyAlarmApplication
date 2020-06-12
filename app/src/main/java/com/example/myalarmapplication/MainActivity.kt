package com.example.myalarmapplication

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var hourPicked : Int? = null
    var minPicked : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_setAlarm.setOnClickListener {
            showPopup()
        }

        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker_alarmTime, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            hourPicked = hour
            minPicked = minute

            setTime(hourPicked!!, minPicked!!)
        }

        btn_configureClock.setOnClickListener {
            val timePicker = TimePickerDialog(this, timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

    }

    fun showPopup() {
        val popupTime = PopupTime()
        val fragMan = supportFragmentManager
        popupTime.show(fragMan, "Set the alarm")
    }

    fun setTime(hours: Int, minutes: Int) {
        val fx_digit = {x: Int -> if(x < 10) "0$x" else x.toString()}
        val nw_hours = fx_digit(hours)
        val nw_mins = fx_digit(minutes)

        txtView_alarmTime.text = "$nw_hours : $nw_mins"
    }


}
