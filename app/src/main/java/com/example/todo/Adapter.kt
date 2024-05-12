package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ActivityViewBinding

class Adapter(private val data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ActivityViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val priority = binding.prority
        val description = binding.description
        val layout = binding.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        // Bind data to views
        holder.title.text = currentItem.title
        holder.priority.text = currentItem.priority
        holder.description.text = currentItem.description

        // Set background color based on priority
        when (currentItem.priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#FFA500"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
        }

        // Handle item click
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateTask::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }
}
