package com.test.reactivecomposeapp.ui.quote_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.reactivecomposeapp.domain.usecase.AddQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.DeleteQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.GetQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.ModifyQuoteUseCase

class QuoteListViewModelFactory(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val addQuoteUseCase: AddQuoteUseCase,
    private val modifyQuoteUseCase: ModifyQuoteUseCase,
    private val deleteQuoteUseCase: DeleteQuoteUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuoteListViewModel(
                getQuotesUseCase = getQuotesUseCase,
                addQuoteUseCase = addQuoteUseCase,
                modifyQuoteUseCase = modifyQuoteUseCase,
                deleteQuoteUseCase = deleteQuoteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}