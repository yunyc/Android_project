package com.example.myapplication.database.memo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface MemoDao{

    @Query("SELECT * FROM memo ORDER BY priority desc")
    LiveData<List<Memo>> selectMemoList();

    @Query("SELECT * FROM memo WHERE id = :memoId")
    Memo selectMemo(int memoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateMemo(Memo memo);

    @Query("DELETE FROM memo WHERE Id = :memoId")
    void deleteMemo(int memoId);

}
