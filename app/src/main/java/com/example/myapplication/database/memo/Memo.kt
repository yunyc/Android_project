package com.example.myapplication.database.memo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Memo {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String? = null
    var content: String? = null
    var insertDate: String? = null
}
