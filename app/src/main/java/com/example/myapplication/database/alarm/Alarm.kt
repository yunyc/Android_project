package com.example.myapplication.database.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Alarm {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String? = null
    var alarmTime: String? = null

}
