package com.example.myapplication.database.alarm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.database.memo.Memo;

import java.util.List;

@Dao
public interface AlarmDao {

    @Query("SELECT * FROM alarm")
    LiveData<List<Alarm>> selectAlarmList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateAlarm(Alarm alarm);

}
