package com.test.reactivecomposeapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    onDrawerClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
            LifeQuotesNavigationRail(
                onDrawerClicked = onDrawerClicked
            )
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
                        }
                    )

                    LifeQuotesDestination.RANDOM_QUOTE -> RandomQuoteScreen(
                        modifier = modifier.weight(1f),
                        randomQuoteUiState = randomQuoteUiState,
                        onRandomQuoteEvent = onRandomQuoteEvent,
                    )

                    LifeQuotesDestination.FAVORITE -> TODO()
                    LifeQuotesDestination.MORE -> TODO()
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

                    LifeQuotesDestination.FAVORITE -> TODO()
                    LifeQuotesDestination.MORE -> TODO()
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