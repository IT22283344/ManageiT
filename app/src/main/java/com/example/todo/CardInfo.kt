package com.example.todo

import android.icu.text.CaseMap.Title
import android.webkit.WebSettings.RenderPriority

data class CardInfo(
    var title: String,
    var priority: String,
    var description: String
)
