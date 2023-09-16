package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao{
    @Insert()
    suspend fun insertTask(todoDao: TodoDao):Long

    @Query("SELECT * FROM ToDoModel WHERE isFinished==0")
    fun getTask():LiveData<ToDoModel>

    @Query("UPDATE ToDoModel SET isFinished=1 WHERE id=:uid")
    fun finishTask(uid:Long)

    @Query("DELETE FROM TODOMODEL WHERE id=:uid")
    fun deleteTask(uid:Long)
}