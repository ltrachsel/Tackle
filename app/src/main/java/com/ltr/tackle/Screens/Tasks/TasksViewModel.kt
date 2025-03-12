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

    fun toggleTaskCompleted(id: String) {
        val updatedTask = taskRepository.toggleTaskCompleted(id) ?: return

        val indexes = findIndexes(id) ?: return
        val (groupIndex, taskIndex) = indexes

        // Create a new task list with the updated task
        val updatedTasks = _taskGroups[groupIndex].tasks.toMutableList().apply {
            this[taskIndex] = updatedTask
        }

        // Create a new TaskGroup with the updated tasks
        _taskGroups[groupIndex] = _taskGroups[groupIndex].copy(tasks = updatedTasks)
    }

    private fun findIndexes(taskId: String): Pair<Int, Int>? {
        for ((groupIndex, taskGroup) in _taskGroups.withIndex()) {
            val taskIndex = taskGroup.tasks.indexOfFirst { it.id == taskId }
            if (taskIndex != -1) {
                return Pair(groupIndex, taskIndex) // Found the task, return indexes
            }
        }
        return null // Task not found
    }
}
