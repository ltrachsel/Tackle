package com.ltr.tackle.Screens.Home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ltr.tackle.Data.Entities.Task
import com.ltr.tackle.Data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    private var _date = MutableStateFlow<LocalDate>(LocalDate.now())
    val date = _date.asStateFlow()

    init {
        initialize()
    }

    fun initialize() {
        _tasks.clear()
        _tasks.addAll(taskRepository.getTasks(_date.value))
    }

    fun toggleTaskCompleted(id: String) {
        val updatedTask = taskRepository.toggleTaskCompleted(id) ?: return

        val taskIndex = tasks.indexOfFirst { it.id == id }
        _tasks[taskIndex] = updatedTask
    }
}