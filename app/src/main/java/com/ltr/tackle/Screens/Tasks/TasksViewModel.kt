package com.ltr.tackle.Screens.Tasks

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.ltr.tackle.Data.Entities.TaskGroup
import com.ltr.tackle.Data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _taskGroups = mutableStateListOf<TaskGroup>()
    val taskGroups: List<TaskGroup> get() = _taskGroups

    init {
        initialize()
    }

    private fun initialize() {
        _taskGroups.clear()
        _taskGroups.addAll(taskRepository.getTaskGroups(false))
    }
}