package com.example.myapplication.ui.main.fragment.memo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.memo.Memo
import com.example.myapplication.ui.register.activity.RegisterActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoFragment : Fragment(), View.OnClickListener {

    private val db = AppDatabase.getInstance(context)
    private var memoViewModel: MemoViewModel? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val root = inflater.inflate(R.layout.fragment_memo, container, false)

        recyclerView = root.findViewById<View>(R.id.memo_recycler) as RecyclerView

        val layoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = layoutManager

        memoViewModel = ViewModelProviders.of(this).get(MemoViewModel::class.java)

        memoViewModel!!.getData(db.memoDao()).observe(this, Observer { memos ->
            val memoAdapter = MemoAdapter(memos, context)
            memoAdapter.setOnItemClickListener {
                val menu = PopupMenu(context, it)
                val view = it;
                menu.menuInflater.inflate(R.menu.alarm_menu, menu.menu)
                menu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { it ->

                    when (it.itemId) {
                        R.id.update -> {
                            val intent1 = Intent(context, RegisterActivity::class.java)
                            intent1.putExtra("REGISTER_MODE", "MEMO")
                            intent1.putExtra("UPDATE_ID", view.getTag() as Int)
                            startActivity(intent1)
                        }
                        R.id.delete -> {
                            lifecycleScope.launch(Dispatchers.IO) {
                                db.memoDao().deleteMemo(view.getTag() as Int)
                            }
                        }

                    }

                    false
                })
                menu.show()
            }

            recyclerView!!.adapter = memoAdapter
        })

        val fab = root.findViewById<View>(R.id.memo_fab) as FloatingActionButton
        fab.setOnClickListener(this)
        return root
    }

    override fun onClick(view: View) {

        val id = view.id
        when (id) {

            R.id.memo_fab -> {
                val intent = Intent(context, RegisterActivity::class.java)
                intent.putExtra("REGISTER_MODE", "MEMO")
                startActivity(intent)
            }
        }
    }

    fun delete() {

    }

}