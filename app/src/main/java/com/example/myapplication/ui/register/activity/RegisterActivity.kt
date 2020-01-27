package com.example.myapplication.ui.register.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity


import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.myapplication.database.memo.MemoDao
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase

import com.example.myapplication.databinding.ActivityRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var binding :ActivityRegisterBinding? = null
    private var registerViewModel: RegisterViewModel? = null


    private var dateSetListner: DatePickerDialog.OnDateSetListener? = null
    private var timeSetListner: TimePickerDialog.OnTimeSetListener? = null
    private var clickedButton : Button? = null
    private var musicButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = AppDatabase.getInstance(this)

        registerViewModel = RegisterViewModel()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding?.register = registerViewModel

        registerViewModel?.activity = this
        registerViewModel?.binding = binding
        registerViewModel?.mode = intent.getStringExtra("REGISTER_MODE")

        this.InitializeDateListener()
        this.InitializeTimeListener()


    }

    fun saveItems() {

        lifecycleScope.launch(Dispatchers.IO) {
            when (registerViewModel?.mode) {

                "SCHEDULE" -> {
                    registerViewModel!!.insertSchedule(db!!.scheduleDao())
                    registerViewModel!!.insertAlarm(db!!.alarmDao())
                }
                "ALARM" -> registerViewModel!!.insertAlarm(db!!.alarmDao())

                "MEMO" -> registerViewModel!!.insertMemo(db!!.memoDao())

            }


        }
        finish()
    }

    fun save(view: View) {
        saveItems()
    }

    fun cancel(view: View) {
        finish()
    }

    fun openMenu(view: View) {
        var menu = PopupMenu(this, view)
        getMenuInflater().inflate(R.menu.alarm_menu, menu.menu)
        menu.setOnMenuItemClickListener { item ->

            when(item.itemId) {
                R.id.update -> {


                    true}
                R.id.delete -> {true}
                else -> {false}
            }


        }

    }




    override fun onBackPressed() {

        var builder = AlertDialog.Builder(this);
        builder.setTitle("내용을 저장하시겠습니까?")        // 제목 설정
                .setMessage("현재 내용을 저장하려면 저장하기를 누르세요")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("네", DialogInterface.OnClickListener() {
                    dialog, id -> saveItems()
                })
                .setNegativeButton("아니요", DialogInterface.OnClickListener() {
                    dialog, id -> finish()
                })

        var dialog = builder.create()
        dialog.show()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return true
    }

    fun openDatePicker(view :View) {
        DatePickerDialog(this, dateSetListner, Calendar.YEAR, Calendar.MONTH, Calendar.DATE).show()
        clickedButton = findViewById<View>(view.id) as Button
    }

    fun openTimePicker(view :View) {
        TimePickerDialog(this, timeSetListner, Calendar.HOUR, Calendar.MINUTE, true).show()
        clickedButton = findViewById<View>(view.id) as Button
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == 1) {
            musicButton!!.setText(data!!.getStringExtra("music"));
        }
    }

    fun InitializeDateListener() {
        dateSetListner = DatePickerDialog.OnDateSetListener {
            view, year, monthOfYear, dayOfMonth -> run {

            clickedButton!!.setText(year.toString() + "." + monthOfYear + "." + dayOfMonth)

            }
        }
    }

    fun InitializeTimeListener() {
        timeSetListner = TimePickerDialog.OnTimeSetListener {
            view, hourOfDay, minute -> run {
            clickedButton!!.setText(hourOfDay.toString() + ":" + minute)
        }

        }
    }


}
