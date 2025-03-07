package com.ltr.tackle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.ltr.tackle.Navigation.BottomNavigation
import com.ltr.tackle.ui.theme.TackleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TackleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TackleTheme {

                val navController = rememberNavController()

                NavigationGraph(
                    navController = navController
                )

                /*
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    NavigationGraph(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
                */
            }
        }
    }
}