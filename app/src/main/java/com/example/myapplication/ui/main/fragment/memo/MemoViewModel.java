package com.example.myapplication.ui.main.fragment.memo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.database.memo.MemoDao;

import java.util.List;

public class MemoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MemoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<List<Memo>> getData(MemoDao memoDao) {
        return memoDao.selectMemoList();
    }
}