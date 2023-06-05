package com.test.reactivecomposeapp.ui.quote_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.ui.AppBar
import com.test.reactivecomposeapp.ui.components.AddModifyQuoteDialog

@Composable
fun QuoteListAndDetailContent(
    modifier: Modifier = Modifier,
    quoteListUiState: QuoteListUiState,
    onClickFavorite: (Quote) -> Unit,
    onClickQuoteRow: (Quote) -> Unit,
    onEvent: (QuoteListIntent) -> Unit,
) {
    val onAddQuoteClick = remember { mutableStateOf(false) }
    val quoteItem = remember { mutableStateOf<Quote?>(null) }
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = "Favorite Quotes",
                showEdit = true
            ) {
                onAddQuoteClick.value = true
                quoteItem.value = quoteListUiState.quotes[quoteListUiState.selectedIndex]
            }
        },
    ) { padding ->
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding() + 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LazyColumn(modifier = modifier.weight(1f)) {
                items(quoteListUiState.quotes) { quote ->
                    QuoteListItem(
                        quote = quote,
                        onClickFavorite = onClickFavorite,
                        onClickQuoteRow = onClickQuoteRow
                    )
                }
            }
            Column(
                modifier = modifier
                    .weight(1f)
            ) {
                if (quoteListUiState.quotes.isNotEmpty())
                    QuoteDetailScreen(
                        quote = quoteListUiState.quotes[quoteListUiState.selectedIndex]
                    )
            }
        }
    }
    if (onAddQuoteClick.value || quoteItem.value != null) {
        AddModifyQuoteDialog(
            quote = quoteItem.value,
            onCancel = { onAddQuoteClick.value = false },
            onDeleteQuoteItem = {
                onEvent(
                    QuoteListIntent.DeleteQuote(
                        id = it
                    )
                )
                onAddQuoteClick.value = false
                quoteItem.value = null
            },
            onModifyComplete = {
                onEvent(
                    QuoteListIntent.ModifyQuote(
                        quote = it
                    )
                )
                onAddQuoteClick.value = false
                quoteItem.value = null
            },
            onAddComplete = {
                onEvent(
                    QuoteListIntent.AddQuote(
                        quote = it
                    )
                )
                onAddQuoteClick.value = false
            }
        )
    }
}
