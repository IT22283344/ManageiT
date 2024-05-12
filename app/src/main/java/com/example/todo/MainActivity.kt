package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.todo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: MyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= Room.databaseBuilder(
            applicationContext,MyDatabase::class.java,"TODO"
        ).build()

        // Set click listeners using View Binding
        binding.add.setOnClickListener {
            val intent = Intent(this, CreateTask::class.java)
            startActivity(intent)
        }

        var calender_btn=findViewById<ImageButton>(R.id.calender_btn)
        calender_btn.setOnClickListener{
            var intent=Intent(this,Calender::class.java)
            startActivity(intent)
        }

        binding.deleteall.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                 database.dao().deleteAll()
            }
            setRecycle()
        }

        setRecycle()
    }

    fun setRecycle(){
        // Set up RecyclerView
        binding.recycleView.adapter = Adapter(DataObject.getAllData())
        binding.recycleView.layoutManager = LinearLayoutManager(this)
    }
}
