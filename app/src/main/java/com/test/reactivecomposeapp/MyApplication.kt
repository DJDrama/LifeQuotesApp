package com.test.reactivecomposeapp

import android.app.Application
import com.test.reactivecomposeapp.data.db.AppDatabase
import com.test.reactivecomposeapp.data.repository.StudentRepositoryImpl

class MyApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { StudentRepositoryImpl(database.studentDao()) }
}