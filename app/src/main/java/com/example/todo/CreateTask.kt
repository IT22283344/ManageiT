package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.databinding.ActivityCreateTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateTask : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var database: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= Room.databaseBuilder(
            applicationContext,MyDatabase::class.java,"TODO"
        ).build()

        binding.saveBtn.setOnClickListener {
            if (binding.createTitle.text.toString().trim().isNotEmpty() &&
                binding.createPriority.text.toString().trim().isNotEmpty() &&
                binding.createDes.text.toString().trim().isNotEmpty()
            ) {
                val title = binding.createTitle.text.toString()
                val description = binding.createDes.text.toString()
                val priority = binding.createPriority.text.toString()
                DataObject.setData(title,description,priority)

                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title,description,priority))

                }

                GlobalScope.launch {
                    Log.i("consout",database.dao().getTasks().toString())
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
