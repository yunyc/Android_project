package com.example.myapplication.ui.main.fragment.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.database.memo.MemoDao;
import com.example.myapplication.database.schedule.Schedule;
import com.example.myapplication.database.schedule.ScheduleDao;

import java.util.List;

public class ScheduleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScheduleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<List<Schedule>> getData(ScheduleDao scheduleDao) {
        return scheduleDao.selectScheduleList();
    }
}