package com.test.reactivecomposeapp.data.repository

import com.test.reactivecomposeapp.data.db.StudentDao
import com.test.reactivecomposeapp.data.db.asDomainList
import com.test.reactivecomposeapp.data.db.asDomainModel
import com.test.reactivecomposeapp.data.db.asEntityModel
import com.test.reactivecomposeapp.domain.model.Student
import com.test.reactivecomposeapp.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepositoryImpl
constructor(
    private val studentDao: StudentDao
) : StudentRepository {
    override fun getStudents(): Flow<List<Student>> {
        return flow {
            studentDao.getAllStudents().collect {
                emit(it.asDomainList())
            }
        }
    }

    override suspend fun showStudent(id: Int?): Student {
        return studentDao.getStudent(id = id).asDomainModel()
    }

    override suspend fun insertStudent(student: Student) {
        studentDao.insert(student.asEntityModel())
    }

    override suspend fun deleteStudent(student: Student) {
        studentDao.delete(studentEntity = student.asEntityModel())
    }

    override suspend fun updateStudent(student: Student) {
        studentDao.update(studentEntity = student.asEntityModel())
    }

}