package com.ltr.tackle.Data.Entities

import androidx.compose.runtime.Stable
import java.time.LocalDate

@Stable
data class Task (
    val id: String,
    var completed: Boolean,
    var title: String,
    var date: LocalDate,
    var description: String
)