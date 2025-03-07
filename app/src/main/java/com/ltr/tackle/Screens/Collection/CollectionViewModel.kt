package com.ltr.tackle.Screens.Collection

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ltr.tackle.Data.Entities.Task
import com.ltr.tackle.Data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    var tasks = MutableStateFlow<List<Task>>(emptyList())
    var counter: Int = 1

    fun loadTasks() {
        tasks.value = taskRepository.getTasks()
    }

    fun addTask(){
        val newTask = taskRepository.addTask(counter)

        newTask?.let {
            tasks.value = tasks.value + it
        }

        counter++
    }
}