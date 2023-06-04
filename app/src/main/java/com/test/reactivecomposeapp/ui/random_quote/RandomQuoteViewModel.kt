package com.test.reactivecomposeapp.ui.random_quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.domain.usecase.GetRandomQuoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RandomQuoteViewModel(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RandomQuoteUiState())
    val uiState = _uiState.asStateFlow()

    init {
        handleIntent(intent = RandomQuoteIntent.GetRandomQuote)
    }

    fun handleIntent(intent: RandomQuoteIntent) {
        when (intent) {
            RandomQuoteIntent.GetRandomQuote -> getRandomQuote()
        }
    }

    private fun getRandomQuote() {
        viewModelScope.launch {
            getRandomQuoteUseCase().collect { randomQuote ->
                _uiState.update {
                    it.copy(
                        quote = randomQuote
                    )
                }
            }
        }
    }

}

data class RandomQuoteUiState(
    val quote: Quote? = null
)

sealed interface RandomQuoteIntent {
    object GetRandomQuote : RandomQuoteIntent
}