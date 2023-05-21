package com.test.reactivecomposeapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE id=:id")
    suspend fun getStudent(id: Int?): StudentEntity

    @Insert
    suspend fun insert(studentEntity: StudentEntity)

    @Update
    suspend fun update(studentEntity: StudentEntity)

    @Delete
    suspend fun delete(studentEntity: StudentEntity)


}