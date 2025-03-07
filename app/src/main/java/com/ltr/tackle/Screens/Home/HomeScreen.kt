package com.ltr.tackle.Screens.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ltr.tackle.Navigation.BottomNavigation
import com.ltr.tackle.R
import com.ltr.tackle.Screens.Components.TaskList
import com.ltr.tackle.Screens.Components.Topbar
import com.ltr.tackle.Screens.Components.TopbarButton

@Composable
fun HomeScreen(
    navController: NavHostController,
    createSubTask: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val date by viewModel.date.collectAsState()
    val tasks = viewModel.tasks

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    LaunchedEffect(Unit) {
        savedStateHandle?.getLiveData<Boolean>("task_created")?.observeForever { taskCreated ->
            if (taskCreated == true) {
                savedStateHandle.remove<Boolean>("task_created") // Clear state after reading
                viewModel.initialize()
            }
        }
    }

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
                    titleId = R.string.top_bar_today
                ) {
                    TopbarButton(
                        imageId = R.drawable.plus,
                        contentDescription = "add",
                        onClickHandler = createSubTask,
                        iconSize = 20.dp
                    )
                }

                TaskList(
                    tasks = tasks,
                    taskOnClick = { task ->
                        viewModel.taskClicked(task)
                    }
                )
            }
        }
    }
}