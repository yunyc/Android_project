package com.example.myapplication.ui.register.memo

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRegisterMemoBinding
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.main.alarm.AlarmViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterMemoActivity : AppCompatActivity() {

    private var binding :ActivityRegisterMemoBinding? = null
    private var registerMemoVM :RegisterMemoVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var id = intent.getIntExtra("UPDATE_ID",0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_memo);
        registerMemoVM = ViewModelProviders.of(this).get(RegisterMemoVM::class.java);
        registerMemoVM!!.activity = this

        binding!!.register = registerMemoVM


        if (id != 0) {

            lifecycleScope.launch(Dispatchers.IO) {
                registerMemoVM!!.memo = MainActivity.db.memoDao().selectMemo(id)
            }

        }



    }

    override fun onBackPressed() {

        var builder = AlertDialog.Builder(this);
        builder.setTitle("내용을 저장하시겠습니까?")        // 제목 설정
                .setMessage("현재 내용을 저장하려면 저장하기를 누르세요")        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("네", DialogInterface.OnClickListener() {
                    dialog, id -> finish()
                })
                .setNegativeButton("아니요", DialogInterface.OnClickListener() {
                    dialog, id -> finish()
                })

        var dialog = builder.create()
        dialog.show()


    }



}
