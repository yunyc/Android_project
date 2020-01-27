package com.example.myapplication.database.schedule

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myapplication.database.alarm.Alarm
import com.example.myapplication.database.memo.Memo
import com.example.myapplication.ui.main.activity.MainActivity
import java.util.*

@Entity
class Schedule :BaseObservable {

    constructor()

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

    @get:Bindable
    var title: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var startDate: String? = MainActivity.date
        set(value) {
            field = value
            notifyPropertyChanged(BR.startDate)
        }

    @get:Bindable
    var endDate: String? = MainActivity.date
        set(value) {
            field = value
            notifyPropertyChanged(BR.endDate)
        }

    @get:Bindable
    var startTime: String? = MainActivity.time
        set(value) {
            field = value
            notifyPropertyChanged(BR.startTime)
        }

    @get:Bindable
    var endTime: String? = MainActivity.time
        set(value) {
            field = value
            notifyPropertyChanged(BR.endTime)
        }


    var insertDate: String? = null
}
