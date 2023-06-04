package com.test.reactivecomposeapp.domain.repository

import com.test.reactivecomposeapp.data.db.QuoteEntity
import com.test.reactivecomposeapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getAllQuotes(): Flow<List<Quote>>
    suspend fun addQuote(quote: Quote)
    suspend fun modifyQuote(quote: Quote)
    suspend fun deleteQuote(id: Int)
    fun getRandomQuote(): Flow<Quote?>
    fun getFavoriteQuotes(): Flow<List<Quote>>
}