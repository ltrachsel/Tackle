package com.ltr.tackle.Screens.Collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ltr.tackle.Data.Entities.Task
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.ltr.tackle.Navigation.BottomNavigation

@Composable
fun CollectionScreen(
    navController: NavController,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    // Observe tasks from the ViewModel
    val tasks by viewModel.tasks.collectAsState(emptyList())

    // Load tasks when the composable is first launched
    LaunchedEffect(Unit) {
        viewModel.loadTasks()  // This fetches tasks and stores them in the ViewModel state
    }


    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AddTaskButton(
                onClickHandler = {
                    viewModel.addTask()
                }
            )

            // Display tasks in a list
            LazyColumn {
                items(tasks) { task ->
                    TaskRow(task)  // Replace with your actual row composable to display task details
                }
            }

        }
    }
}

@Composable
fun TaskRow(task: Task) {
    // Simple row displaying the task details
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = task.title, fontWeight = FontWeight.Bold)
        Text(text = task.description)
        Text(text = "Due: idk bro")
    }
}

@Composable
fun AddTaskButton(
    onClickHandler: () -> Unit
){
    Button(
        onClick = onClickHandler
    ) {
        Text(text = "Add Task")
    }
}