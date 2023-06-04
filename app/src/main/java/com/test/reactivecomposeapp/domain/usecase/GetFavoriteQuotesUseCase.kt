package com.test.reactivecomposeapp.domain.usecase

import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteQuotesUseCase(
    private val repository: QuoteRepository
){
    operator fun invoke(): Flow<List<Quote>> {
        return repository.getFavoriteQuotes()
    }
}