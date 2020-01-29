package com.example.myapplication.ui.register.schedule

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.memo.Memo
import com.example.myapplication.database.schedule.Schedule
import petrov.kristiyan.colorpicker.ColorPicker
import java.util.*

class RegisterScheduleVM : ViewModel() {

    var schedule = Schedule()
    var activity : AppCompatActivity? = null
    var c :Calendar? = null

    fun openDatePicker(view : View) {
        c = Calendar.getInstance()

        var clickedButton = activity!!.findViewById<View>(view.id) as Button
        var dateSetListner = DatePickerDialog.OnDateSetListener {

            view, year, monthOfYear, dayOfMonth -> run {
            clickedButton!!.setText(year.toString() + "." + monthOfYear + "." + dayOfMonth)

            }
        }

        DatePickerDialog(activity!!, dateSetListner,
                c!!.get(Calendar.YEAR), c!!.get(Calendar.MONTH + 1), c!!.get(Calendar.DATE)).show()

    }

    fun openTimePicker(view : View) {
        var clickedButton = activity!!.findViewById<View>(view.id) as Button

        var timeSetListner = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            run {
                clickedButton!!.setText(hourOfDay.toString() + ":" + minute)
            }
        }

        TimePickerDialog(activity, timeSetListner,
                c!!.get(Calendar.HOUR_OF_DAY), c!!.get(Calendar.MINUTE), true).show()

    }
}
