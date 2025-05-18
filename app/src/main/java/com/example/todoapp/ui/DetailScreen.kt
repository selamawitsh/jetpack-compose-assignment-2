package com.example.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(todoId: Int, navController: NavController, viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()
    val todo = todos.find { it.id == todoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        todo?.let {
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                Text("Title: ${it.title}", style = MaterialTheme.typography.titleMedium)
                Text("Status: ${if (it.completed) "Completed" else "Pending"}", style = MaterialTheme.typography.bodyMedium)
                Text("User ID: ${it.userId}", style = MaterialTheme.typography.bodyMedium)
                Text("Todo ID: ${it.id}", style = MaterialTheme.typography.bodyMedium)
            }
        } ?: Text(
            "Todo not found.",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

