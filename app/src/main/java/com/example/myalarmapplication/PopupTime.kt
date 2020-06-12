package com.example.myalarmapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.popuptime.*

class PopupTime : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var myView = inflater.inflate(R.layout.popuptime, container, false)

        var saveButton = myView.findViewById<Button>(R.id.btn_saveTime)
        var timePicked = myView.findViewById<TimePicker>(R.id.timePicker_alarmTime)
        timePicked.setIs24HourView(true)
        Log.d("Showing", "Time Picker")
        saveButton.setOnClickListener {

            //This is to set what to do when the button in the popup is clicked
            val mainAct = activity as MainActivity
            mainAct.setTime(timePicked.hour, timePicked.minute)
            this.dismiss()
        }

        return myView
    }

}