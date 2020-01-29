package com.example.myapplication.ui.register.alarm

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.alarm.Alarm
import com.example.myapplication.database.memo.Memo
import com.example.myapplication.databinding.ActivityRegisterAlarmBinding
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.register.music.MusicActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import petrov.kristiyan.colorpicker.ColorPicker
import java.util.*

class RegisterAlarmVM : ViewModel() {

    var alarm = Alarm()
    var activity :AppCompatActivity? = null
    var c :Calendar? = null
    var binding  :ActivityRegisterAlarmBinding? = null

    fun openMusicSelect() {
        var musicIntent = Intent(activity, MusicActivity::class.java)
        activity?.startActivityForResult(musicIntent, 1)
    }

    fun openTimePicker(view : View) {
        c = Calendar.getInstance()

        var clickedButton = activity!!.findViewById<View>(view.id) as Button

        var timeSetListner = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            run {
                clickedButton!!.setText(hourOfDay.toString() + ":" + minute)
            }
        }

        TimePickerDialog(activity, timeSetListner,
                c!!.get(Calendar.HOUR_OF_DAY), c!!.get(Calendar.MINUTE), true).show()

    }

    fun checkWeek() {
        binding?.sun?.isChecked = true
        binding?.mon?.isChecked = false
        binding?.tue?.isChecked = false
        binding?.wed?.isChecked = false
        binding?.thu?.isChecked = false
        binding?.fri?.isChecked = false
        binding?.sat?.isChecked = true
    }

    fun checkDay() {
        binding?.sun?.isChecked = false
        binding?.mon?.isChecked = true
        binding?.tue?.isChecked = true
        binding?.wed?.isChecked = true
        binding?.thu?.isChecked = true
        binding?.fri?.isChecked = true
        binding?.sat?.isChecked = false
    }

    fun checkAll() {
        binding?.sun?.isChecked = true
        binding?.mon?.isChecked = true
        binding?.tue?.isChecked = true
        binding?.wed?.isChecked = true
        binding?.thu?.isChecked = true
        binding?.fri?.isChecked = true
        binding?.sat?.isChecked = true


    }

    fun save() {

        viewModelScope.launch(Dispatchers.IO) {
            MainActivity.db.alarmDao().insertOrUpdateAlarm(alarm)
        }

        activity!!.finish()
    }

    fun cancel() {
        activity!!.finish()
    }


}
