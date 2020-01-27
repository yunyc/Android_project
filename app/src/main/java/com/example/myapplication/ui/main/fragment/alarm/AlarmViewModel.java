package com.example.myapplication.ui.main.fragment.alarm;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.database.alarm.Alarm;
import com.example.myapplication.database.alarm.AlarmDao;
import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.database.memo.MemoDao;

import java.util.List;

public class AlarmViewModel extends ViewModel {

    public ObservableField<String> mText;

    public AlarmViewModel() {
        mText = new ObservableField<String>();


    }


    public LiveData<List<Alarm>> getData(AlarmDao alarmDao) {
        return alarmDao.selectAlarmList();
    }
}