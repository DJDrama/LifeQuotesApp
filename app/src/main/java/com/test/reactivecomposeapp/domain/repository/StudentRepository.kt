package com.test.reactivecomposeapp.domain.repository

import com.test.reactivecomposeapp.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getStudents(): Flow<List<Student>>

    suspend fun showStudent(id: Int?): Student

    suspend fun insertStudent(student: Student)

    suspend fun deleteStudent(student: Student)

    suspend fun updateStudent(student: Student)
}