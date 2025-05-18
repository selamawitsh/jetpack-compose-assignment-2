package com.example.todoapp.repository

import com.example.todoapp.data.TodoDao
import com.example.todoapp.model.Todo
import com.example.todoapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(private val todoDao: TodoDao) {

    // Lazy val is not suspend, but calling suspend function directly here is wrong
    // Changed to suspend function to fetch cached todos
    suspend fun getLocalTodos(): List<Todo> {
        return withContext(Dispatchers.IO) {
            todoDao.getAllTodos()
        }
    }

    suspend fun refreshTodos(): List<Todo> {
        return withContext(Dispatchers.IO) {
            val remoteTodos = RetrofitInstance.api.getTodos()
            todoDao.insertAll(remoteTodos)
            remoteTodos
        }
    }

    suspend fun getCachedTodos(): List<Todo> {
        return withContext(Dispatchers.IO) {
            todoDao.getAllTodos()
        }
    }
}

