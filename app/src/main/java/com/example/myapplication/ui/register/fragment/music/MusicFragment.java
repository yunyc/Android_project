package com.example.myapplication.ui.register.fragment.music;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MusicFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;

    private MusicViewModel musicViewModel;
    private Button musicButton;


    public static MusicFragment newInstance(int index) {
        MusicFragment fragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        musicViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_music, container, false);

        recyclerView = root.findViewById(R.id.music_recycler);
        musicButton = root.findViewById(R.id.music);
        Button musicSave = (Button) root.findViewById(R.id.music_save);
        musicSave.setOnClickListener(this);



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        musicViewModel.getMusic(root.getContext()).observe(this, new Observer<ArrayList<Music>>() {
            @Override
            public void onChanged(ArrayList<Music> music) {

                musicAdapter = new MusicAdapter(music);
                recyclerView.setAdapter(musicAdapter);

            }
    });
        SearchView searchView = (SearchView) root.findViewById(R.id.music_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                musicViewModel.getSearchMusic(root.getContext(), s).observe(getActivity(), new Observer<ArrayList<Music>>() {
                    @Override
                    public void onChanged(ArrayList<Music> music) {

                        musicAdapter = new MusicAdapter(music);
                        recyclerView.setAdapter(musicAdapter);

                    }
                });

                return false;
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.music_save:
                Intent intent = new Intent();
                intent.putExtra("music", musicAdapter.getSelectedTitle());

                getActivity().setResult(1, intent);
                getActivity().finish();

        }
    }






}