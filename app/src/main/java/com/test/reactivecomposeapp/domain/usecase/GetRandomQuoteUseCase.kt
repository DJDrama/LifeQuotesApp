package com.test.reactivecomposeapp.domain.usecase

import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class GetRandomQuoteUseCase(val repository: QuoteRepository) {

    operator fun invoke(): Flow<Quote?> {
        return repository.getRandomQuote()
    }
}