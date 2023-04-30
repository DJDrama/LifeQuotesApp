package com.test.reactivecomposeapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TutorDao {

    @Insert
    suspend fun insert(tutors: List<Tutor>)

    @Query("DELETE FROM tutor")
    suspend fun removeAllTutors()
}