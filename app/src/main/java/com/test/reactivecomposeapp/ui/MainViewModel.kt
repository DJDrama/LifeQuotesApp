package com.test.reactivecomposeapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnSelectDestination -> onSelectDestination(selectedDestination = intent.selectedDestination)
        }
    }

    private fun onSelectDestination(selectedDestination: LifeQuotesDestination) {
        _uiState.update {
            it.copy(
                selectedDestination = selectedDestination
            )
        }
    }
}

data class MainUiState(
    val selectedDestination: LifeQuotesDestination = LifeQuotesDestination.QUOTES
)

sealed interface MainIntent {
    data class OnSelectDestination(val selectedDestination: LifeQuotesDestination) : MainIntent
}