package com.test.reactivecomposeapp.ui.student_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.reactivecomposeapp.domain.repository.StudentRepository

class StudentViewModelFactory(private val repository: StudentRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}