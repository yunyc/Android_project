package com.example.myapplication.ui.alarm;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.database.Injection;
import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.database.memo.MemoDao;

import java.util.List;

public class AlarmViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AlarmViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");

    }


    public LiveData<List<Memo>> getData(MemoDao memoDao) {
        return memoDao.selectMemoList();
    }
}