package com.example.myapplication.ui.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScheduleFragment extends Fragment implements View.OnClickListener {

    private ScheduleViewModel scheduleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                ViewModelProviders.of(this).get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        final TextView textView = root.findViewById(R.id.text);
        scheduleViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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