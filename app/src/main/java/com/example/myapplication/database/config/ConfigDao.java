package com.example.myapplication.database.config;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.database.memo.Memo;

import java.util.List;

@Dao
public interface ConfigDao {

    @Query("SELECT * FROM config")
    LiveData<List<Config>> selectConfigList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateConfig(Config config);

}
