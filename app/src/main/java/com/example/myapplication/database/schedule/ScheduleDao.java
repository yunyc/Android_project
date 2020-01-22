package com.example.myapplication.database.schedule;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.database.memo.Memo;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Query("SELECT * FROM schedule")
    LiveData<List<Schedule>> selectScheduleList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateSchedule(Schedule schedule);

}
