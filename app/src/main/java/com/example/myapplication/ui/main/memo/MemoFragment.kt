package com.example.myapplication.ui.main.memo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ui.register.memo.RegisterMemoActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoFragment : Fragment(), View.OnClickListener {

    private val db = AppDatabase.getInstance(context)
    private var memoViewModel: MemoViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var memoAdapter: MemoAdapter? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val root = inflater.inflate(R.layout.fragment_memo, container, false)
        val layoutManager = LinearLayoutManager(context)

        recyclerView = root.findViewById<View>(R.id.memo_recycler) as RecyclerView
        recyclerView!!.layoutManager = layoutManager

        memoViewModel = ViewModelProviders.of(this).get(MemoViewModel::class.java)
        memoViewModel!!.context = context;
        memoViewModel!!.getData(db.memoDao()).observe(this, Observer { memos ->
            memoAdapter = MemoAdapter(memos, context)
            memoAdapter!!.setOnItemClickListener(memoViewModel!!.getItemClickListener())
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
                val intent = Intent(context, RegisterMemoActivity::class.java)
                intent.putExtra("REGISTER_MODE", "MEMO")
                startActivity(intent)
            }
        }
    }




}