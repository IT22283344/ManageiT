package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.todo.databinding.ActivityUpdateTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateTask : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= Room.databaseBuilder(
            applicationContext,MyDatabase::class.java,"TODO"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val description = DataObject.getData(pos).description
            val priority = DataObject.getData(pos).priority
            binding.createTitle.setText(title)
            binding.createDes.setText(description)
            binding.createPriority.setText(priority)

            binding.deleteBtn.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos+1,title,description,priority))
                }
                myIntent()
            }

            binding.updateBtn.setOnClickListener {
                val newTitle = binding.createTitle.text.toString()
                val newDes = binding.createDes.text.toString()
                val newPriority = binding.createPriority.text.toString()
                DataObject.updateData(pos, newTitle,newDes, newPriority)
                Toast.makeText(this,title+" "+description+priority,Toast.LENGTH_LONG).show()
                GlobalScope.launch {
                    database.dao().updateTask(Entity(
                        pos+1,binding.createTitle.text.toString(),
                        binding.createDes.text.toString(),
                        binding.createPriority.text.toString()
                    ))
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
