package com.test.reactivecomposeapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.reactivecomposeapp.domain.usecase.GetFavoriteQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.ModifyQuoteUseCase

class FavoriteQuoteViewModelFactory(
    private val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase,
    private val modifyQuoteUseCase: ModifyQuoteUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteQuoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteQuoteViewModel(
                modifyQuoteUseCase = modifyQuoteUseCase,
                getFavoriteQuotesUseCase = getFavoriteQuotesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}