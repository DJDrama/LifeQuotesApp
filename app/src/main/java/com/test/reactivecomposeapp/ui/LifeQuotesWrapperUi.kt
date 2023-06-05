package com.test.reactivecomposeapp.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.test.reactivecomposeapp.ui.favorite.FavoriteQuoteIntent
import com.test.reactivecomposeapp.ui.favorite.FavoriteQuoteUiState
import com.test.reactivecomposeapp.ui.quote_list.QuoteListIntent
import com.test.reactivecomposeapp.ui.quote_list.QuoteListUiState
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteIntent
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteUiState
import com.test.reactivecomposeapp.ui.utils.ContentType
import com.test.reactivecomposeapp.ui.utils.NavigationType
import kotlinx.coroutines.launch

@Composable
fun LifeQuotesWrapperUI(
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
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet {
                    NavigationDrawerContent(
                        selectedDestination = mainUiState.selectedDestination,
                        onClickNavigationDrawerTab = {
                            onMainEvent(MainIntent.OnSelectDestination(it))
                        })
                }
            }
        ) {
            LifeQuotesAppContent(
                navigationType = navigationType,
                contentType = contentType,
                mainUiState = mainUiState,
                onMainEvent = onMainEvent,
                quoteListUiState = quoteListUiState,
                onQuoteListEvent = onQuoteListEvent,
                randomQuoteUiState = randomQuoteUiState,
                onRandomQuoteEvent = onRandomQuoteEvent,
                favoriteQuoteUiState = favoriteQuoteUiState,
                onFavoriteQuoteEvent = onFavoriteQuoteEvent,
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    NavigationDrawerContent(
                        selectedDestination = mainUiState.selectedDestination,
                        onClickNavigationDrawerTab = {
                            onMainEvent(MainIntent.OnSelectDestination(it))
                        },
                        onDrawerClicked = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            },
            drawerState = drawerState
        ) {
            LifeQuotesAppContent(
                navigationType = navigationType,
                contentType = contentType,
                mainUiState = mainUiState,
                onMainEvent = onMainEvent,
                quoteListUiState = quoteListUiState,
                onQuoteListEvent = onQuoteListEvent,
                randomQuoteUiState = randomQuoteUiState,
                onRandomQuoteEvent = onRandomQuoteEvent,
                favoriteQuoteUiState = favoriteQuoteUiState,
                onFavoriteQuoteEvent = onFavoriteQuoteEvent,
                onDrawerClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}