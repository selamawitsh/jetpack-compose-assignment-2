package com.example.todoapp.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.model.Todo
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: TodoRepository

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        val dao = TodoDatabase.getDatabase(app).todoDao()
        repository = TodoRepository(dao)
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val local = repository.getCachedTodos()
                _todos.value = local

                val fresh = repository.refreshTodos()
                _todos.value = fresh
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to fetch todos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
