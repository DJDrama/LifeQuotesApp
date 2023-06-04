package com.test.reactivecomposeapp.domain.usecase

import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.repository.QuoteRepository

class ModifyQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(
        quote: Quote
    ) {
        repository.modifyQuote(quote = quote)
    }
}