package com.test.reactivecomposeapp.ui.favorite

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.R
import com.test.reactivecomposeapp.domain.model.Quote
import com.test.reactivecomposeapp.ui.AppBar
import com.test.reactivecomposeapp.ui.quote_list.QuoteListItem

@Composable
fun FavoriteListContent(
    modifier: Modifier = Modifier,
    favoriteQuoteUiState: FavoriteQuoteUiState,
    onClickFavorite: (Quote) -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.favorite),
            )
        },
    ) { padding ->
        if(favoriteQuoteUiState.favoriteQuotes.isNotEmpty()){
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding() + 8.dp)
            ) {
                items(items = favoriteQuoteUiState.favoriteQuotes) { quote ->
                    QuoteListItem(
                        quote = quote,
                        onClickFavorite = onClickFavorite
                    ) {
                    }
                }
            }
        }else{
            Text(
                modifier = modifier
                    .padding(all = padding.calculateTopPadding() + 16.dp)
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.no_favorite)
            )
        }
    }

}

