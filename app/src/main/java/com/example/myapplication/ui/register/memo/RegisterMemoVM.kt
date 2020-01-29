package com.example.myapplication.ui.register.memo

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.memo.Memo
import com.example.myapplication.databinding.ActivityRegisterMemoBinding
import com.example.myapplication.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import petrov.kristiyan.colorpicker.ColorPicker

class RegisterMemoVM : ViewModel() {

    var memo = Memo();
    var activity : AppCompatActivity? = null
    var binding : ActivityRegisterMemoBinding? = null

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
                        v.setBackgroundColor(color)
                    }

                    override fun onCancel() {
                        // Cancel 버튼 클릭 시 이벤트
                    }
                }).show()  // dialog 생성
    }

    fun saveMemo() {
        viewModelScope.launch(Dispatchers.IO) {
            MainActivity.db.memoDao().insertOrUpdateMemo(memo)
        }
        activity!!.finish()
    }

    fun cancel() {
        activity!!.finish()
    }

}
