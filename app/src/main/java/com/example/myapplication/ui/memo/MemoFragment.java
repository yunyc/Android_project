package com.example.myapplication.ui.memo;

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

import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.ViewModelProviders;

public class MemoFragment extends Fragment implements View.OnClickListener {

    private MemoViewModel memoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        memoViewModel =
                ViewModelProviders.of(this).get(MemoViewModel.class);


        View root = inflater.inflate(R.layout.fragment_memo, container, false);
        final TextView textView = root.findViewById(R.id.text_home);


        memoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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