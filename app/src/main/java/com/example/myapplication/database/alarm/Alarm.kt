package com.example.myapplication.database.alarm

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Alarm :BaseObservable {

    constructor()

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @get:Bindable
    var title: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var alarmTime: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.alarmTime)
        }

    @get:Bindable
    var musicTitle: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.musicTitle)
        }

    @get:Bindable
    var volume: Integer? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.volume)
        }

    @get:Bindable
    var vibration: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.vibration)
        }

    var musicPath: String? = null
    var startWeek: String? = null



}
