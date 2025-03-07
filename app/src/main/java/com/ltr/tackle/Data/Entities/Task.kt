package com.ltr.tackle.Data.Entities

import java.time.LocalDate

data class Task (
    val id: String,
    var completed: Boolean,
    var title: String,
    var date: LocalDate,
    var description: String
)