package com.test.reactivecomposeapp.ui.random_quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.reactivecomposeapp.domain.usecase.AddQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.DeleteQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.GetQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.GetRandomQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.ModifyQuoteUseCase

class RandomQuoteViewModelFactory(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomQuoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RandomQuoteViewModel(
                getRandomQuoteUseCase = getRandomQuoteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}