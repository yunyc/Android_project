package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity


import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.myapplication.database.memo.MemoDao
import android.app.TimePickerDialog
import android.content.Intent
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.database.Injection
import com.example.myapplication.database.alarm.Alarm
import com.example.myapplication.database.alarm.AlarmDao
import com.example.myapplication.database.memo.Memo
import com.example.myapplication.database.schedule.Schedule
import com.example.myapplication.database.schedule.ScheduleDao
import com.example.myapplication.ui.Layout
import kotlinx.coroutines.launch


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val injection = Injection.getInjection(this);

    private var memoDao: MemoDao? = null
    private var alarmDao: AlarmDao? = null
    private var scheduleDao: ScheduleDao? = null

    private var memo = Memo()
    private var alarm = Alarm()
    private var schedule = Schedule()

    private var dateSetListner: DatePickerDialog.OnDateSetListener? = null
    private var timeSetListner: TimePickerDialog.OnTimeSetListener? = null
    private var clickedButton : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        this.InitializeDateListener()
        this.InitializeTimeListener()

        memoDao = injection.getMemoDao()
        alarmDao = injection.getAlarmDao();
        scheduleDao = injection.getScheduleDao();

        var linear: LinearLayout = findViewById<View>(R.id.linearLayout) as LinearLayout

        var intent = intent;
        var mode = intent.getStringExtra("REGISTER_MODE")

        when (mode) {
            "MEMO" -> linear.addView(Layout(applicationContext, R.layout.register_memo))
            "ALARM" -> {
                linear.addView(Layout(applicationContext, R.layout.register_alarm))

                var music = findViewById<View>(R.id.music) as Button

                music?.setOnClickListener(this)
            }
            "SCHEDULE" -> {
                linear.addView(Layout(applicationContext, R.layout.register_schedule))
                linear.addView(Layout(applicationContext, R.layout.register_alarm))

                var startDate = findViewById<View>(R.id.startDate) as Button
                var endDate = findViewById<View>(R.id.endDate) as Button
                var startTime = findViewById<View>(R.id.startTime) as Button
                var endTime = findViewById<View>(R.id.endTime) as Button

                startDate?.setOnClickListener(this)
                endDate?.setOnClickListener(this)
                startTime?.setOnClickListener(this)
                endTime?.setOnClickListener(this)
            }



        }




    }

    override fun onClick(view: View) {

        var id = view.id

        when (id) {
            R.id.startDate, R.id.endDate -> {
                var dialog = DatePickerDialog(this, dateSetListner, 2019, 5, 24)
                dialog.show()
                clickedButton = findViewById<View>(id) as Button
            }
            R.id.startTime, R.id.endTime -> {
                var dialog = TimePickerDialog(this, timeSetListner, 5, 24, true)
                dialog.show()
                clickedButton = findViewById<View>(id) as Button
            }

            R.id.register_save -> {
                var memoTitle = findViewById<View>(R.id.memo_title) as EditText
                var memoContent = findViewById<View>(R.id.memo_content) as EditText


                memo.title = memoTitle.toString()
                memo.content = memoContent.toString()

                lifecycleScope.launch { memoDao!!.insertOrUpdateMemo(memo) }


            }

            R.id.music -> {
                var musicIntent = Intent(this, MusicActivity::class.java)
                startActivity(musicIntent)
            }


        }
    }

    fun InitializeDateListener() {
        dateSetListner = DatePickerDialog.OnDateSetListener {
            view, year, monthOfYear, dayOfMonth -> run {


            clickedButton!!.setText(year.toString() + "년" + monthOfYear + "월" + dayOfMonth + "일")

            }
        }
    }

    fun InitializeTimeListener() {
        timeSetListner = TimePickerDialog.OnTimeSetListener {
            view, hourOfDay, minute -> run {
            clickedButton!!.setText(hourOfDay.toString() + "시" + minute + "분")
        }

        }
    }


}
