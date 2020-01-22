package com.example.myapplication.database.schedule

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myapplication.database.alarm.Alarm
import com.example.myapplication.database.memo.Memo

@Entity
class Schedule {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ForeignKey(entity = Alarm::class,
                parentColumns = arrayOf("id'"),
                childColumns = arrayOf("alarmId"))
    var alarmId: Int = 0

    @ForeignKey(entity = Memo::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("memoId"))
    var memoId: Int = 0

    var title: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var startTime: String? = null
    var endTime: String? = null
    var insertDate: String? = null
}
