package com.example.myapplication.ui.register.music;

import android.content.Context;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MusicViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<ArrayList<Music>> getMusic(Context context) {

        Music music = new Music(context);

        MutableLiveData mData = new MutableLiveData<>();
        mData.setValue(music.getMusicList());
        return mData;
    }

    public LiveData<ArrayList<Music>> getSearchMusic(Context context, String keyword) {

        Music music = new Music(context);
        List<Music> searchList = new ArrayList<Music>();

        MutableLiveData mData = new MutableLiveData<>();

        for (Music item :music.getMusicList()) {

            if (item.getMusicTitle().startsWith(keyword)) {
                searchList.add(item);
            }
        }
        mData.setValue(searchList);
        return mData;
    }
}