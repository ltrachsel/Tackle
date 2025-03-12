package com.ltr.tackle

enum class Screen{
    HOME,
    TASKS,
    GOALS,
    CREATEEDITTASK
}

sealed class NavigationItem(val route: String, val iconResource: Int, val label: Int) {
    object Home : NavigationItem(Screen.HOME.name, R.drawable.icon_home, R.string.navigation_today)
    object Tasks: NavigationItem(Screen.TASKS.name, R.drawable.icon_tasks, R.string.navigation_tasks)
    object Settings: NavigationItem(Screen.GOALS.name, R.drawable.icon_award, R.string.navigation_goals)
}

sealed class SubScreen(val route: String) {
    object CreateEditTask : SubScreen(Screen.CREATEEDITTASK.name)
}