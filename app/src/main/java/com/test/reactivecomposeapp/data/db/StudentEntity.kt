package com.test.reactivecomposeapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.reactivecomposeapp.domain.model.Student

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "contact") val contact: String,
    @ColumnInfo(name = "description") val description: String
)

fun StudentEntity.asDomainModel(): Student {
    return Student(
        id = this.id,
        name = this.name,
        age = this.age,
        gender = this.gender,
        contact = this.contact,
        description = this.description
    )
}

fun List<StudentEntity>.asDomainList(): List<Student> = map {
    it.asDomainModel()
}

fun Student.asEntityModel(): StudentEntity {
    return StudentEntity(
        id = this.id,
        name = this.name,
        age = this.age,
        gender = this.gender,
        contact = this.contact,
        description = this.description
    )
}