package com.test.reactivecomposeapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.usecase.GetFavoriteQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.ModifyQuoteUseCase
import com.test.reactivecomposeapp.ui.quote_list.QuoteListIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteQuoteViewModel(
    private val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase,
    private val modifyQuoteUseCase: ModifyQuoteUseCase,
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

    fun handleIntent(intent: FavoriteQuoteIntent) {
        when (intent) {
            is FavoriteQuoteIntent.SetFavoriteQuote -> modifyQuote(
                quote = intent.quote.copy(
                    favorite = intent.quote.favorite.not()
                )
            )

            is FavoriteQuoteIntent.SelectQuote -> selectQuote(quote = intent.quote)
        }
    }
    private fun selectQuote(quote: Quote) {
        _uiState.update {
            it.copy(
                selectedIndex = it.favoriteQuotes.indexOf(element = quote)
            )
        }
    }

    private fun modifyQuote(quote: Quote) {
        viewModelScope.launch {
            modifyQuoteUseCase(quote = quote)
        }
    }

}

data class FavoriteQuoteUiState(
    val selectedIndex: Int = 0,
    val favoriteQuotes: List<Quote> = emptyList()
)

sealed interface FavoriteQuoteIntent {
    data class SetFavoriteQuote(val quote: Quote) : FavoriteQuoteIntent
    data class SelectQuote(val quote: Quote) : FavoriteQuoteIntent
}