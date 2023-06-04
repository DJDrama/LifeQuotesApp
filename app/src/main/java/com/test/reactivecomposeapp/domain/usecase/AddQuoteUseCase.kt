package com.test.reactivecomposeapp.domain.usecase

import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.repository.QuoteRepository

class AddQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(
        quote: Quote
    ) {
        repository.addQuote(quote = quote)
    }
}