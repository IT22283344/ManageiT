package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Calender : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        var backbtn =findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener{
            val intent9= Intent(this,MainActivity::class.java)
            startActivity(intent9)
        }
    }
}