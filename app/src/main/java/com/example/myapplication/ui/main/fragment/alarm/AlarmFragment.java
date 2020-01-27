package com.example.myapplication.ui.main.fragment.alarm;

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
import com.example.myapplication.database.AppDatabase;
import com.example.myapplication.database.alarm.Alarm;
import com.example.myapplication.ui.register.activity.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AlarmFragment extends Fragment implements View.OnClickListener{

    private AppDatabase db;
    private AlarmViewModel alarmViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        db = AppDatabase.getInstance(getContext());

        View root = inflater.inflate(R.layout.fragment_alarm, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.alarm_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        alarmViewModel =
                ViewModelProviders.of(this).get(AlarmViewModel.class);

        alarmViewModel.getData(db.alarmDao()).observe(this, new Observer<List<Alarm>>() {
                    @Override
                    public void onChanged(List<Alarm> alarms) {
                        AlarmAdapter alarmAdapter = new AlarmAdapter(alarms);
                        recyclerView.setAdapter(alarmAdapter);
                    }
                });

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.alarm_fab);
        fab.setOnClickListener(this);

        //binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_alarm);
        //binding.setAlarm(alarmViewModel);

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