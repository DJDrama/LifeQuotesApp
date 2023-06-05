package com.test.reactivecomposeapp.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.ui.AppBar
import com.test.reactivecomposeapp.ui.quote_list.QuoteDetailScreen
import com.test.reactivecomposeapp.ui.quote_list.QuoteListItem

@Composable
fun FavoriteListAndDetailContent(
    modifier: Modifier = Modifier,
    favoriteQuoteUiState: FavoriteQuoteUiState,
    onClickFavorite: (Quote) -> Unit,
    onClickQuoteRow: (Quote) -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = "Favorite Quotes",
            )
        },
    ) { padding ->
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding() + 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LazyColumn(modifier = modifier.weight(1f)) {
                items(favoriteQuoteUiState.favoriteQuotes) { quote ->
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
                if (favoriteQuoteUiState.favoriteQuotes.isNotEmpty())
                    QuoteDetailScreen(
                        quote = favoriteQuoteUiState.favoriteQuotes[favoriteQuoteUiState.selectedIndex]
                    )
            }
        }
    }
}