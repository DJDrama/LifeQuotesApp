package com.test.reactivecomposeapp.ui.quote_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.domain.model.Quote

@Composable
fun QuoteListAndDetailContent(
    modifier: Modifier = Modifier,
    quoteListUiState: QuoteListUiState,
    onClickFavorite: (Quote) -> Unit,
    selectedItemIndex: Int = 0
) {

    Row(modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        LazyColumn(modifier = modifier.weight(1f)) {
            items(quoteListUiState.quotes) { quote ->
                QuoteListItem(
                    quote = quote,
                    onClickFavorite = onClickFavorite
                ) {
                }
            }
        }
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            if (quoteListUiState.quotes.isNotEmpty())
                QuoteDetailScreen(
                    quote = quoteListUiState.quotes[selectedItemIndex]
                )
        }
    }
}
