package com.ltr.tackle.Screens.Tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ltr.tackle.Navigation.BottomNavigation
import com.ltr.tackle.R
import com.ltr.tackle.Screens.Components.TaskGroupsList
import com.ltr.tackle.Screens.Components.Topbar

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val taskGroups = viewModel.taskGroups

    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Topbar(
                    titleId = R.string.screen_tasks_heading
                )

                TaskGroupsList(
                    taskGroups = taskGroups
                )

            }
        }
    }
}