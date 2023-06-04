package com.test.reactivecomposeapp.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
    onRandomQuoteEvent: (RandomQuoteIntent) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val selectedDestination = LifeQuotesDestination.QUOTES

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet {
                    NavigationDrawerContent(selectedDestination)
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
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    NavigationDrawerContent(
                        selectedDestination = selectedDestination,
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
                onDrawerClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}