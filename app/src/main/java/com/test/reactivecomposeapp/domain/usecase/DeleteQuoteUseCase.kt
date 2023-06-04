package com.test.reactivecomposeapp.domain.usecase

import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.repository.QuoteRepository

class DeleteQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(
        id: Int
    ) {
        repository.deleteQuote(id = id)
    }
}