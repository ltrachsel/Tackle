package com.ltr.tackle.Data.Entities

import java.time.LocalDate

data class TaskGroup (
    val date: LocalDate,
    val tasks: List<Task>
)