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
