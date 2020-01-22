package com.example.myapplication.ui.alarm;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.example.myapplication.database.Injection;
import com.example.myapplication.database.memo.Memo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AlarmFragment extends Fragment implements View.OnClickListener{

    private Injection injection;

    private AlarmViewModel alarmViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        injection = Injection.getInjection(getContext());

        View root = inflater.inflate(R.layout.fragment_alarm, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.alarm_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        alarmViewModel =
                ViewModelProviders.of(this).get(AlarmViewModel.class);

        alarmViewModel.getData(injection.getMemoDao()).observe(this, new Observer<List<Memo>>() {
                    @Override
                    public void onChanged(List<Memo> memos) {
                        MyAdapter myAdapter = new MyAdapter(memos);
                        recyclerView.setAdapter(myAdapter);
                    }
                });

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.alarm_fab);
        fab.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {

            case R.id.alarm_fab: {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("REGISTER_MODE", "ALARM");
                startActivity(intent);
                break;
            }
        }
    }
}