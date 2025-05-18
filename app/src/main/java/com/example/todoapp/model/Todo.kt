package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)
