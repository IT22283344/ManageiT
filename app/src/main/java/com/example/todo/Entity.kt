package com.example.todo

import android.icu.text.CaseMap.Title
import android.webkit.WebSettings.RenderPriority
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TODO")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var description: String,
    var priority: String
)