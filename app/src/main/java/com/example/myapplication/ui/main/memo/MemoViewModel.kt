package com.example.myapplication.ui.main.memo

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.PopupMenu
import androidx.lifecycle.*

import com.example.myapplication.R

import com.example.myapplication.database.memo.Memo
import com.example.myapplication.database.memo.MemoDao
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.register.memo.RegisterMemoActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel : ViewModel() {

    init {}

    var context :Context? = null

    fun getData(memoDao: MemoDao): LiveData<List<Memo>> {
        return memoDao.selectMemoList()
    }

    fun getItemClickListener() :View.OnClickListener{
        return View.OnClickListener {
            val menu = PopupMenu(context, it)
            val view = it;

            menu.menuInflater.inflate(R.menu.alarm_menu, menu.menu)
            menu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { it ->

                when (it.itemId) {
                    R.id.update -> {

                        val intent = Intent(context, RegisterMemoActivity::class.java)
                        intent.putExtra("UPDATE_ID", view.getTag() as Int)
                        context!!.startActivity(intent)
                    }

                    R.id.delete -> {

                        viewModelScope.launch(Dispatchers.IO) {
                            MainActivity.db.memoDao().deleteMemo(view.getTag() as Int)
                        }
                    }

                }

                false


            })
            menu.show()
        }
    }


}