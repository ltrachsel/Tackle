package com.ltr.tackle.Screens.Components

import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun convertDateToString(
    date: LocalDate
): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.format(formatter)
}