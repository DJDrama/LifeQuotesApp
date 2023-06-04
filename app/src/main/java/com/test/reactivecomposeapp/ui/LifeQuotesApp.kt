package com.test.reactivecomposeapp.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.test.reactivecomposeapp.ui.quote_list.QuoteListIntent
import com.test.reactivecomposeapp.ui.quote_list.QuoteListUiState
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteIntent
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteUiState
import com.test.reactivecomposeapp.ui.utils.ContentType
import com.test.reactivecomposeapp.ui.utils.DevicePosture
import com.test.reactivecomposeapp.ui.utils.NavigationType

@Composable
fun LifeQuotesApp(
    windowSize: WindowWidthSizeClass,
    foldingDevicePosture: DevicePosture,
    mainUiState: MainUiState,
    onMainEvent: (MainIntent) -> Unit,
    quoteListUiState: QuoteListUiState,
    onQuoteListEvent: (QuoteListIntent) -> Unit,
    randomQuoteUiState : RandomQuoteUiState,
    onRandomQuoteEvent: (RandomQuoteIntent) ->Unit,
) {
    val navigationType: NavigationType
    val contentType: ContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
            contentType = if (foldingDevicePosture != DevicePosture.NormalPosture) {
                ContentType.LIST_AND_DETAIL
            } else {
                ContentType.LIST_ONLY
            }
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = if (foldingDevicePosture is DevicePosture.BookPosture) {
                NavigationType.NAVIGATION_RAIL
            } else {
                NavigationType.PERMANENT_NAVIGATION_DRAWER
            }
            contentType = ContentType.LIST_AND_DETAIL
        }

        else -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.LIST_ONLY
        }
    }
    LifeQuotesWrapperUI(
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





