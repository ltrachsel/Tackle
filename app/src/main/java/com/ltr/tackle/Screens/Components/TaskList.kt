package com.ltr.tackle.Screens.Components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ltr.tackle.Data.Entities.Task
import com.ltr.tackle.Data.Entities.TaskGroup
import com.ltr.tackle.R
import java.time.LocalDate

@Composable
fun TaskList(
    tasks: List<Task>,
    taskOnClick: (String) -> Unit
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
                val onClick = remember(task.id) { { taskOnClick(task.id) } }

                TaskListItem(
                    task = task,
                    onClick = onClick
                )
            }
        }
    }
}


@Composable
fun TaskListItem(
    task: Task,
    onClick: () -> Unit,
    onDelete: (() -> Unit)? = null
) {
    Log.d("Recomposition!", "Recomposition")

    Row (
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable(
                    onClick = onClick,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
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

        if (onDelete != null) {
            Image(
                painter = painterResource(R.drawable.icon_trash),
                contentDescription = "trash",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}