package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.todoapp.ui.DetailScreen
import com.example.todoapp.ui.ListScreen
import com.example.todoapp.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TodoViewModel = viewModel()
            val navController = rememberNavController()

            NavHost(navController, startDestination = "list") {
                composable("list") {
                    ListScreen(navController, viewModel)
                }
                composable("detail/{todoId}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
                    id?.let { DetailScreen(it, navController, viewModel) }
                }
            }
        }
    }
}
