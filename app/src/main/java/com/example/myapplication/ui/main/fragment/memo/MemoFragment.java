package com.example.myapplication.ui.main.fragment.memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.myapplication.R;
import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.ui.register.activity.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoFragment extends Fragment implements View.OnClickListener {

    private AppDatabase db = AppDatabase.getInstance(getContext());
    private MemoViewModel memoViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_memo, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.memo_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        memoViewModel =
                ViewModelProviders.of(this).get(MemoViewModel.class);

        memoViewModel.getData(db.memoDao()).observe(this, new Observer<List<Memo>>() {
            @Override
            public void onChanged(List<Memo> memos) {
                MemoAdapter memoAdapter = new MemoAdapter(memos);
                recyclerView.setAdapter(memoAdapter);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.memo_fab);
        fab.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {

            case R.id.memo_fab: {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("REGISTER_MODE", "MEMO");
                startActivity(intent);
                break;
            }
        }
    }
}