package com.ltr.tackle.Screens.Collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun CollectionScreen(
    navController: NavController,
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val taskGroups = viewModel.taskGroups
    val scrollState = rememberScrollState()

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
                    titleId = R.string.screen_collection_heading
                )

                TaskGroupsList(
                    taskGroups = taskGroups
                )

            }
        }
    }
}