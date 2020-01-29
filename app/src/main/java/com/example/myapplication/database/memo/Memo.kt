package com.example.myapplication.database.memo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Memo : BaseObservable {

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
    var content: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.content)
        }

    @get:Bindable
    var priority: Int? = 3
        set(value) {
            field = value
            notifyPropertyChanged(BR.priority)
        }


    var insertDate: String? = null






}
