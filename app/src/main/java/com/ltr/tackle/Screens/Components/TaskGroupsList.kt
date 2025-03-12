package com.ltr.tackle.Screens.Components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ltr.tackle.Data.Entities.TaskGroup
import com.ltr.tackle.R
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskGroupsList(
    taskGroups: List<TaskGroup>,
    taskOnClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            taskGroups.forEach { group ->
                stickyHeader {
                    TaskGroupHeading(group.date)
                }

                items(group.tasks, key = { it.id }) { task ->
                    TaskListItem(
                        task = task,
                        onClick = { taskOnClick(task.id) },
                        onDelete = { }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskGroupHeading(
    date: LocalDate
) {
    val formattedDate = convertDateToString(date)
    val label = getDateLabel(date)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 10.dp)
    ) {
        if (label == null) {
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(R.color.light_gray),
            thickness = 1.dp
        )
    }
}

@Composable
fun getDateLabel(date: LocalDate): String? {
    return when (date) {
        LocalDate.now() -> stringResource(R.string.screen_tasks_heading_today)
        LocalDate.now().plusDays(1) -> stringResource(R.string.screen_tasks_heading_tomorrow)
        else -> null
    }
}
