package com.test.reactivecomposeapp.ui.random_quote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.R
import com.test.reactivecomposeapp.ui.AppBar
import com.test.reactivecomposeapp.ui.quote_list.QuoteDetailScreen

@Composable
fun RandomQuoteScreen(
    modifier: Modifier = Modifier,
    randomQuoteUiState: RandomQuoteUiState,
    onRandomQuoteEvent: (RandomQuoteIntent) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.random_quote),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                {
                    onRandomQuoteEvent(RandomQuoteIntent.GetRandomQuote)
                },
                modifier = modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    Icons.Rounded.Refresh,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = stringResource(id = R.string.random_quote)
                )
            }
        }
    ) { padding ->
        randomQuoteUiState.quote?.let {
            Column(modifier = modifier.padding(top = padding.calculateTopPadding() + 8.dp)) {
                QuoteDetailScreen(
                    quote = it
                )
            }
        } ?: run {
            Text(
                modifier = modifier
                    .padding(all = padding.calculateTopPadding() + 16.dp)
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.no_quotes)
            )
        }
    }
}