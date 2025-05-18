package com.example.todoapp.data


import androidx.room.*
import com.example.todoapp.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<Todo>)
}

