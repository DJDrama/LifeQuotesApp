package com.test.reactivecomposeapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.usecase.GetFavoriteQuotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteQuoteViewModel(
    private val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteQuoteUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getFavoriteQuotes()
    }

    private fun getFavoriteQuotes() {
        viewModelScope.launch {
            getFavoriteQuotesUseCase().collect { favoriteQuotes ->
                _uiState.update {
                    it.copy(
                        favoriteQuotes = favoriteQuotes
                    )
                }
            }
        }
    }
}

data class FavoriteQuoteUiState(
    val favoriteQuotes: List<Quote> = emptyList()
)