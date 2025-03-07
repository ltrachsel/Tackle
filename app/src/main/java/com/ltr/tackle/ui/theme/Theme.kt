package com.ltr.tackle.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(

)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    surface = Color.White,




    /* Other default colors to override

    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TackleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    /*
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/


    val systemUiController = rememberSystemUiController()


    // Define color schemes manually (or use your existing ones)
    val statusBarColor = Color.White
    val navBarColor = Color.White


    // Define color schemes manually (or use your existing ones)
    //val statusBarColor = if (darkTheme) Color.Black else Color.White
    //val navBarColor = if (darkTheme) Color.Black else Color.White

    // Apply system bar colors
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = true // Light icons in dark mode, dark icons in light mode
    )

    systemUiController.setNavigationBarColor(
        color = navBarColor,
        darkIcons = true
    )



    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}