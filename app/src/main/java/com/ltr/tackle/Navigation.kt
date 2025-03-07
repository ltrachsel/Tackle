package com.ltr.tackle

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen{
    HOME,
    COLLECTION,
    SETTINGS,
    CREATEEDITTASK
}

sealed class NavigationItem(val route: String, val icon: ImageVector, val label: Int) {
    object Home : NavigationItem(Screen.HOME.name, Icons.Default.Home, R.string.navigation_today)
    object Collection: NavigationItem(Screen.COLLECTION.name, Icons.Default.Menu, R.string.navigation_collection)
    object Settings: NavigationItem(Screen.SETTINGS.name, Icons.Default.Settings, R.string.navigation_settings)
}

sealed class SubScreen(val route: String) {
    object CreateEditTask : SubScreen(Screen.CREATEEDITTASK.name)
}