package com.test.reactivecomposeapp.ui.quote_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.usecase.AddQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.DeleteQuoteUseCase
import com.test.reactivecomposeapp.domain.usecase.GetQuotesUseCase
import com.test.reactivecomposeapp.domain.usecase.ModifyQuoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuoteListViewModel
constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val addQuoteUseCase: AddQuoteUseCase,
    private val modifyQuoteUseCase: ModifyQuoteUseCase,
    private val deleteQuoteUseCase: DeleteQuoteUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(value = QuoteListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllQuotes()
    }

    fun handleIntent(intent: QuoteListIntent) {
        when (intent) {
            is QuoteListIntent.AddQuote -> addQuote(quote = intent.quote)
            is QuoteListIntent.ModifyQuote -> modifyQuote(quote = intent.quote)
            is QuoteListIntent.DeleteQuote -> deleteQuote(id = intent.id)
            is QuoteListIntent.SetFavoriteQuote -> modifyQuote(
                quote = intent.quote.copy(
                    favorite = intent.quote.favorite.not()
                )
            )

            is QuoteListIntent.SelectQuote -> selectQuote(intent.quote)
        }
    }

    private fun selectQuote(quote: Quote) {
        _uiState.update {
            it.copy(
                selectedIndex = it.quotes.indexOf(element = quote)
            )
        }
    }

    private fun getAllQuotes() {
        viewModelScope.launch {
            getQuotesUseCase().collect { quotes ->
                _uiState.update {
                    it.copy(
                        quotes = quotes
                    )
                }
            }
        }
    }

    private fun addQuote(quote: Quote) {
        viewModelScope.launch {
            addQuoteUseCase(quote = quote)
        }
    }

    private fun modifyQuote(quote: Quote) {
        viewModelScope.launch {
            modifyQuoteUseCase(quote = quote)
        }
    }

    private fun deleteQuote(id: Int) {
        viewModelScope.launch {
            deleteQuoteUseCase(id = id)
        }
    }

}

sealed interface QuoteListIntent {
    data class DeleteQuote(val id: Int) : QuoteListIntent
    data class ModifyQuote(val quote: Quote) : QuoteListIntent
    data class AddQuote(val quote: Quote) : QuoteListIntent
    data class SetFavoriteQuote(val quote: Quote) : QuoteListIntent
    data class SelectQuote(val quote: Quote) : QuoteListIntent
}

data class QuoteListUiState(
    val selectedIndex: Int = 0,
    val quotes: List<Quote> = emptyList()
)