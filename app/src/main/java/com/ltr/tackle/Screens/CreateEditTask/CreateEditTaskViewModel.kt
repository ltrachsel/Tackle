package com.ltr.tackle.Screens.CreateEditTask

import androidx.lifecycle.ViewModel
import com.ltr.tackle.Data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEditTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private var _title = MutableStateFlow("")
    var title = _title.asStateFlow()
    private var _isTitleValid = MutableStateFlow<Boolean?>(null) // default no error on UI
    val isTitleValid = _isTitleValid.asStateFlow()

    private var _description = MutableStateFlow("")
    val description = _description.asStateFlow()
    private var _isDescriptionValid = MutableStateFlow<Boolean?>(null)
    val isDescriptionValid = _isDescriptionValid.asStateFlow()

    private var _date = MutableStateFlow("")
    val date = _date.asStateFlow()
    private var _isDateValid = MutableStateFlow<Boolean?>(null) // default no error on UI
    val isDateValid = _isDateValid.asStateFlow()

    fun setTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun setDate(newDate: String) {
        _date.value = newDate
    }

    fun setDescription(newDescription: String) {
        _description.value = newDescription
    }


    fun createTask(): Boolean {
        validateTitle(_title.value)
        validateDate(_date.value)
        validateDescription(_description.value)

        if (_isTitleValid.value == false || _isDateValid.value == false || _isDescriptionValid.value == false){
            return false
        }

        taskRepository.createTask(_title.value, _description.value, _date.value)
        return true
    }

    private fun validateDate(date: String) {
        if (date.isBlank()) {
            _isDateValid.value = false
        } else {
            _isDateValid.value = true
        }
    }

    private fun validateTitle(title: String) {
        if (title.isBlank()) {
            _isTitleValid.value = false
        } else {
            _isTitleValid.value = true
        }
    }

    private fun validateDescription(description: String) {

    }
}