package com.test.reactivecomposeapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.More
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.test.reactivecomposeapp.R

@Composable
fun LifeQuotesBottomNavigationBar(
    modifier: Modifier = Modifier,
    mainUiState: MainUiState,
    onClickTabPosition: (LifeQuotesDestination) -> Unit
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = mainUiState.selectedDestination == LifeQuotesDestination.QUOTES,
            onClick = {
                onClickTabPosition(LifeQuotesDestination.QUOTES)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = stringResource(id = R.string.life_quotes_list)
                )
            }
        )
        NavigationBarItem(
            selected = mainUiState.selectedDestination == LifeQuotesDestination.RANDOM_QUOTE,
            onClick = {
                onClickTabPosition(LifeQuotesDestination.RANDOM_QUOTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Icecream,
                    contentDescription = stringResource(id = R.string.random_quote)
                )
            }
        )
        NavigationBarItem(
            selected = mainUiState.selectedDestination == LifeQuotesDestination.FAVORITE,
            onClick = {
                onClickTabPosition(LifeQuotesDestination.FAVORITE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        )
        NavigationBarItem(
            selected = mainUiState.selectedDestination == LifeQuotesDestination.MORE,
            onClick = {
                onClickTabPosition(LifeQuotesDestination.MORE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.More,
                    contentDescription = stringResource(id = R.string.more)
                )
            }
        )
    }
}