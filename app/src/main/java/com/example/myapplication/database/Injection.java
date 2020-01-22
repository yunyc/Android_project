/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication.database;

import android.content.Context;

import com.example.myapplication.database.alarm.AlarmDao;
import com.example.myapplication.database.memo.MemoDao;
import com.example.myapplication.database.schedule.Schedule;
import com.example.myapplication.database.schedule.ScheduleDao;

/**
 * Enables injection of data sources.
 */
public class Injection {

    private AppDatabase database;

    private Injection(Context context) {
        database = AppDatabase.getInstance(context);
    }

    public static Injection getInjection(Context context) {
        return new Injection(context);
    }


    public MemoDao getMemoDao() {
        return database.memoDao();
    }

    public AlarmDao getAlarmDao() {
        return database.alarmDao();
    }

    public ScheduleDao getScheduleDao() {
        return database.scheduleDao();
    }



}
