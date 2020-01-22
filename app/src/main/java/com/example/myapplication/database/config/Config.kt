package com.example.myapplication.database.config

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Config {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
