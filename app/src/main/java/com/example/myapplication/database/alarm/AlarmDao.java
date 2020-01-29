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

    @Query("SELECT * FROM alarm WHERE id = :alarmId")
    Alarm selectAlarm(int alarmId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateAlarm(Alarm alarm);

    @Query("DELETE FROM memo WHERE id = :alarmId")
    void DeleteAlarm(int alarmId);

}
