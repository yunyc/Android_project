package com.example.myapplication.ui.register.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import petrov.kristiyan.colorpicker.ColorPicker
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View

import com.example.myapplication.database.memo.Memo
import com.example.myapplication.database.memo.MemoDao
import com.example.myapplication.databinding.ActivityRegisterBinding
import android.R
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.example.myapplication.BR
import com.example.myapplication.database.alarm.Alarm
import com.example.myapplication.database.alarm.AlarmDao
import com.example.myapplication.database.schedule.Schedule
import com.example.myapplication.database.schedule.ScheduleDao
import java.util.*
import kotlin.collections.ArrayList


class RegisterViewModel :BaseObservable {

    var loopDay :ArrayList<String> = ArrayList<String>()

    var activity :Activity? = null
    var binding :ActivityRegisterBinding? = null
    var mode :String? = null

    var memo = Memo()
    var alarm = Alarm()
    var schedule = Schedule()

    constructor() {}


    fun insertMemo(memoDao: MemoDao) {
        memoDao.insertOrUpdateMemo(memo)
    }

    fun insertSchedule(scheduleDao: ScheduleDao) {
        scheduleDao.insertOrUpdateSchedule(schedule)
    }

    fun insertAlarm(alarmDao: AlarmDao) {

        if (binding!!.sun!!.isChecked) loopDay.add("1")
        if (binding!!.mon!!.isChecked) loopDay.add("2")
        if (binding!!.tue!!.isChecked) loopDay.add("3")
        if (binding!!.wed!!.isChecked) loopDay.add("4")
        if (binding!!.thu!!.isChecked) loopDay.add("5")
        if (binding!!.fri!!.isChecked) loopDay.add("6")
        if (binding!!.sat!!.isChecked) loopDay.add("7")
        alarm.startWeek = loopDay.joinToString(",")

        alarmDao.insertOrUpdateAlarm(alarm)

    }


    fun openMusicSelect() {
        var musicIntent = Intent(activity, MusicActivity::class.java)
        activity?.startActivityForResult(musicIntent, 1)
    }

    fun openColorPicker(v: View) {
        val colorPicker = ColorPicker(activity)  // ColorPicker 객체 생성
        val colors = ArrayList<String>()  // Color 넣어줄 list

        colors.add("#ffab91")
        colors.add("#F48FB1")
        colors.add("#ce93d8")
        colors.add("#b39ddb")
        colors.add("#9fa8da")
        colors.add("#90caf9")
        colors.add("#81d4fa")
        colors.add("#80deea")
        colors.add("#80cbc4")
        colors.add("#c5e1a5")
        colors.add("#e6ee9c")
        colors.add("#fff59d")
        colors.add("#ffe082")
        colors.add("#ffcc80")
        colors.add("#bcaaa4")

        colorPicker.setColors(colors)  // 만들어둔 list 적용
                .setColumns(5)  // 5열로 설정
                .setRoundColorButton(true)  // 원형 버튼으로 설정
                .setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        var col = activity!!.findViewById<View>(v.id)
                        col.setBackgroundColor(color)

                    }

                    override fun onCancel() {
                        // Cancel 버튼 클릭 시 이벤트
                    }
                }).show()  // dialog 생성
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



}
