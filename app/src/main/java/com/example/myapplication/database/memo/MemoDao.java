package com.example.myapplication.database.memo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface MemoDao{

    @Query("SELECT * FROM memo")
    LiveData<List<Memo>> selectMemoList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateMemo(Memo memo);

}
