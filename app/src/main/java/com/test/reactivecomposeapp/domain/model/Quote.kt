package com.test.reactivecomposeapp.domain.model

data class Quote(
    val id: Int? = null,
    val quote: String,
    val author: String,
    val description: String,
    val date: String,
    val favorite: Boolean,
)
