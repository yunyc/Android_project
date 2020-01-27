package com.example.myapplication.ui.main.fragment.schedule;

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
import com.example.myapplication.database.schedule.Schedule;
import com.example.myapplication.ui.register.activity.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ScheduleFragment extends Fragment implements View.OnClickListener {

    private AppDatabase db = AppDatabase.getInstance(getContext());
    private ScheduleViewModel scheduleViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        scheduleViewModel =
                ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.schedule_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        scheduleViewModel.getData(db.scheduleDao()).observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                ScheduleAdapter scheduleAdapter = new ScheduleAdapter(schedules, getContext());
                recyclerView.setAdapter(scheduleAdapter);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.schedule_fab);
        fab.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {

            case R.id.schedule_fab: {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("REGISTER_MODE", "SCHEDULE");
                startActivity(intent);
                break;
            }
        }
    }
}