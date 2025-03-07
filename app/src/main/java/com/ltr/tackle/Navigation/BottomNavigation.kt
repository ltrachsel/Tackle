package com.ltr.tackle.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ltr.tackle.NavigationItem
import com.ltr.tackle.R

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val navItems = listOf(
        NavigationItem.Collection,
        NavigationItem.Home,
        NavigationItem.Settings
    )

    Column(
        modifier = modifier
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = colorResource(R.color.light_gray)
        )

        NavigationBar(
            containerColor = Color.White
        ) {

            navItems.forEach { navItem ->
                NavigationBarItem(
                    icon = { Icon(navItem.icon, contentDescription = stringResource(navItem.label)) },
                    label = { Text(stringResource(navItem.label)) },
                    selected = navController.currentDestination?.route == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(0)
                        }
                    }
                )
            }

        }
    }


}