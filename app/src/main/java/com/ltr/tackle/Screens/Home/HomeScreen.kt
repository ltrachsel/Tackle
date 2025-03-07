package com.ltr.tackle.Screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ltr.tackle.Data.Entities.Task
import com.ltr.tackle.Navigation.BottomNavigation
import com.ltr.tackle.R
import com.ltr.tackle.Screens.Components.Topbar
import com.ltr.tackle.Screens.Components.TopbarButton
import java.time.LocalDate

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

                TodoList(
                    taskOnClickHandler = { task ->
                        viewModel.taskClicked(task)
                    },
                    tasks = tasks
                )
            }
        }
    }
}

@Composable
fun TodoList(
    taskOnClickHandler: (Task) -> Unit,
    tasks: List<Task>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(tasks, key = { it.id }) { task ->

                //val isLastTask = task == tasks.lastOrNull()

                TodoItem(
                    onClickHandler = { taskOnClickHandler(task) },
                    task = task
                )
            }
        }
    }
}


@Composable
fun TodoItem(
    onClickHandler: () -> Unit,
    task: Task
) {
    Row (
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClickHandler,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
                .size(25.dp, 25.dp)
                .then(
                    if (task.completed) {
                        Modifier
                            .border(1.dp, colorResource(R.color.green), RoundedCornerShape(9.dp))
                            .background(colorResource(R.color.green), RoundedCornerShape(9.dp))
                    } else {
                        Modifier
                            .border(
                                1.dp,
                                colorResource(R.color.light_gray),
                                RoundedCornerShape(9.dp)
                            )
                            .background(Color.White, RoundedCornerShape(9.dp))
                    }
                )
        )

        Column(
            modifier = Modifier
                .padding(start = 15.dp)
        ) {

            val hasDescription = task.description.isNotBlank()

            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                color = if(task.completed) colorResource(R.color.green) else colorResource(R.color.black),
                modifier = Modifier
                    .padding(bottom = if (hasDescription) 5.dp else 0.dp)
                    .height(25.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )

            if(hasDescription) {
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemTest() {
    val today = LocalDate.now()
    val task = Task("123", false, "Write blog post", today, "Topic: Jetpack Compose best practices")

    TodoItem({}, task)
}