package com.ltr.tackle.Screens.Collection

import androidx.lifecycle.ViewModel
import com.ltr.tackle.Data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

}