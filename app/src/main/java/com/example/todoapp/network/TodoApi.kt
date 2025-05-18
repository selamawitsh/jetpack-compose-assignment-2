package com.example.todoapp.network

import com.example.todoapp.model.Todo
import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
