package com.example.myapplication.ui.register.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRegisterAlarmBinding
import com.example.myapplication.ui.register.memo.RegisterMemoVM
import com.example.myapplication.ui.register.schedule.RegisterScheduleActivity

class RegisterAlarmActivity : AppCompatActivity() {

    private var binding: ActivityRegisterAlarmBinding? = null
    private var registerAlarmVM: RegisterAlarmVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_alarm)

        registerAlarmVM = ViewModelProviders.of(this).get(RegisterAlarmVM::class.java)
        registerAlarmVM!!.activity = this

        binding!!.register = registerAlarmVM




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == 1) {
            binding!!.music.setText(data!!.getStringExtra("music"));
        }
    }




}
