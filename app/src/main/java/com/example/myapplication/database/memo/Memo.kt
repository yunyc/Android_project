package com.example.myapplication.database.memo

import android.widget.EditText
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.ui.main.activity.MainActivity
import com.example.myapplication.ui.register.activity.RegisterActivity

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


    var insertDate: String? = RegisterActivity.date






}
