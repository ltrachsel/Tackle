package com.ltr.tackle.Screens.Goals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ltr.tackle.Navigation.BottomNavigation

@Composable
fun GoalsScreen(
    navController: NavHostController
) {
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
            Text(text = "Settings Screen")

        }
    }
}