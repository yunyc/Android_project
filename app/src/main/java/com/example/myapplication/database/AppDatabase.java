package com.example.myapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.database.alarm.Alarm;
import com.example.myapplication.database.alarm.AlarmDao;
import com.example.myapplication.database.config.Config;
import com.example.myapplication.database.config.ConfigDao;
import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.database.memo.MemoDao;
import com.example.myapplication.database.schedule.Schedule;
import com.example.myapplication.database.schedule.ScheduleDao;


@Database(entities = {Memo.class, Alarm.class, Config.class, Schedule.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    /*
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    */
    public abstract MemoDao memoDao();

    public abstract AlarmDao alarmDao();

    public abstract ConfigDao configDao();

    public abstract ScheduleDao scheduleDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "test9.db")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}


