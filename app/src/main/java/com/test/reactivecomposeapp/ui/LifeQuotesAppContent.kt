package com.test.reactivecomposeapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.test.reactivecomposeapp.ui.favorite.FavoriteListAndDetailContent
import com.test.reactivecomposeapp.ui.favorite.FavoriteListContent
import com.test.reactivecomposeapp.ui.favorite.FavoriteQuoteIntent
import com.test.reactivecomposeapp.ui.favorite.FavoriteQuoteUiState
import com.test.reactivecomposeapp.ui.more.MoreScreen
import com.test.reactivecomposeapp.ui.quote_list.QuoteListAndDetailContent
import com.test.reactivecomposeapp.ui.quote_list.QuoteListContent
import com.test.reactivecomposeapp.ui.quote_list.QuoteListIntent
import com.test.reactivecomposeapp.ui.quote_list.QuoteListUiState
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteIntent
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteScreen
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteUiState
import com.test.reactivecomposeapp.ui.utils.ContentType
import com.test.reactivecomposeapp.ui.utils.NavigationType

@Composable
fun LifeQuotesAppContent(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    contentType: ContentType,
    mainUiState: MainUiState,
    onMainEvent: (MainIntent) -> Unit,
    quoteListUiState: QuoteListUiState,
    onQuoteListEvent: (QuoteListIntent) -> Unit,
    randomQuoteUiState: RandomQuoteUiState,
    onRandomQuoteEvent: (RandomQuoteIntent) -> Unit,
    favoriteQuoteUiState: FavoriteQuoteUiState,
    onFavoriteQuoteEvent: (FavoriteQuoteIntent) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
            LifeQuotesNavigationRail(
                onDrawerClicked = onDrawerClicked
            ) {
                onMainEvent(MainIntent.OnSelectDestination(it))
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            if (contentType == ContentType.LIST_AND_DETAIL) {
                when (mainUiState.selectedDestination) {
                    LifeQuotesDestination.QUOTES -> QuoteListAndDetailContent(
                        modifier = modifier.weight(1f),
                        quoteListUiState = quoteListUiState,
                        onClickFavorite = {
                            onQuoteListEvent(QuoteListIntent.SetFavoriteQuote(it))
                        },
                        onClickQuoteRow = {
                            onQuoteListEvent(QuoteListIntent.SelectQuote(it))
                        },
                    ){
                        onQuoteListEvent(it)
                    }

                    LifeQuotesDestination.RANDOM_QUOTE -> RandomQuoteScreen(
                        modifier = modifier.weight(1f),
                        randomQuoteUiState = randomQuoteUiState,
                        onRandomQuoteEvent = onRandomQuoteEvent,
                    )

                    LifeQuotesDestination.FAVORITE -> FavoriteListAndDetailContent(
                        modifier = modifier.weight(1f),
                        favoriteQuoteUiState = favoriteQuoteUiState,
                        onClickFavorite = {
                            onFavoriteQuoteEvent(FavoriteQuoteIntent.SetFavoriteQuote(it))
                        },
                        onClickQuoteRow = {
                            onFavoriteQuoteEvent(FavoriteQuoteIntent.SelectQuote(it))
                        },
                    )

                    LifeQuotesDestination.MORE -> MoreScreen(
                        modifier = modifier.weight(1f)
                    )
                }

            } else {
                when (mainUiState.selectedDestination) {
                    LifeQuotesDestination.QUOTES -> QuoteListContent(
                        modifier = modifier.weight(1f),
                        quoteListUiState = quoteListUiState,
                        onClickFavorite = {
                            onQuoteListEvent(QuoteListIntent.SetFavoriteQuote(it))
                        }
                    ) {
                        onQuoteListEvent(it)
                    }

                    LifeQuotesDestination.RANDOM_QUOTE -> RandomQuoteScreen(
                        modifier = modifier.weight(1f),
                        randomQuoteUiState = randomQuoteUiState,
                        onRandomQuoteEvent = onRandomQuoteEvent,
                    )

                    LifeQuotesDestination.FAVORITE -> FavoriteListContent(
                        modifier = modifier.weight(1f),
                        favoriteQuoteUiState = favoriteQuoteUiState,
                        onClickFavorite = {
                            onFavoriteQuoteEvent(FavoriteQuoteIntent.SetFavoriteQuote(it))
                        }
                    )

                    LifeQuotesDestination.MORE -> MoreScreen(
                        modifier = modifier.weight(1f)
                    )
                }
            }

            AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION) {
                LifeQuotesBottomNavigationBar(
                    mainUiState = mainUiState
                ) {
                    onMainEvent(MainIntent.OnSelectDestination(it))
                }
            }
        }
    }
}

@Composable
fun ListAndDetailComponents() {

}