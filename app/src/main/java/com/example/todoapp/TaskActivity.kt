package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.todoapp.databinding.ActivityTaskBinding

const val DB_NAME="todo.db"
class TaskActivity : AppCompatActivity() {

    val db by lazy {
        AppDatabase.getDatabase(this)
    }

    private lateinit var binding:ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}