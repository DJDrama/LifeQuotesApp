package com.test.reactivecomposeapp.ui.quote_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.R
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.ui.AppBar
import com.test.reactivecomposeapp.ui.components.AddModifyQuoteDialog

@Composable
fun QuoteListContent(
    modifier: Modifier = Modifier,
    quoteListUiState: QuoteListUiState,
    onClickFavorite: (Quote) -> Unit,
    onEvent: (QuoteListIntent) -> Unit
) {
    val onAddQuoteClick = remember { mutableStateOf(false) }
    val quoteItem = remember { mutableStateOf<Quote?>(null) }
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.life_quotes_list),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                {
                    onAddQuoteClick.value = true
                },
                modifier = modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    Icons.Rounded.Add,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = stringResource(id = R.string.add_quote)
                )
            }
        }
    ) { padding ->
        if (quoteListUiState.quotes.isNotEmpty()) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding() + 8.dp)
            ) {
                items(items = quoteListUiState.quotes) { quote ->
                    QuoteListItem(
                        quote = quote,
                        onClickFavorite = onClickFavorite
                    ) {
                        quoteItem.value = it
                    }
                }
            }
        } else {
            Text(
                modifier = modifier
                    .padding(all = padding.calculateTopPadding() + 16.dp)
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.no_quotes)
            )
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

