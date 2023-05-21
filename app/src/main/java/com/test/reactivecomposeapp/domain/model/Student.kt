package com.test.reactivecomposeapp.domain.model

data class Student(
    val id: Int? = null,
    val name: String,
    val age: Int,
    val gender: String,
    val contact: String,
    val description: String
)