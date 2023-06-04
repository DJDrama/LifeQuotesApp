package com.test.reactivecomposeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.test.reactivecomposeapp.MyApplication
import com.test.reactivecomposeapp.ui.quote_list.QuoteListViewModel
import com.test.reactivecomposeapp.ui.quote_list.QuoteListViewModelFactory
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteViewModel
import com.test.reactivecomposeapp.ui.random_quote.RandomQuoteViewModelFactory
import com.test.reactivecomposeapp.ui.theme.ReactiveComposeAppTheme
import com.test.reactivecomposeapp.ui.utils.DevicePosture
import com.test.reactivecomposeapp.ui.utils.isBookPosture
import com.test.reactivecomposeapp.ui.utils.isSeparating
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devicePostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature = layoutInfo.displayFeatures
                    .filterIsInstance<FoldingFeature>()
                    .firstOrNull()
                when {
                    isBookPosture(foldingFeature) -> DevicePosture.BookPosture(hingePosition = foldingFeature.bounds)
                    isSeparating(foldingFeature) -> DevicePosture.Separating(
                        hingePosition = foldingFeature.bounds,
                        orientation = foldingFeature.orientation
                    )

                    else -> DevicePosture.NormalPosture
                }
            }.stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )
        setContent {
            ReactiveComposeAppTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                val devicePosture = devicePostureFlow.collectAsState().value

                val mainViewModel: MainViewModel = viewModel()
                val mainUiState = mainViewModel.uiState.collectAsStateWithLifecycle().value

                val quoteListViewModel: QuoteListViewModel = viewModel(
                    factory = QuoteListViewModelFactory(
                        getQuotesUseCase = (application as MyApplication).getQuotesUseCase,
                        addQuoteUseCase = (application as MyApplication).addQuoteUseCase,
                        modifyQuoteUseCase = (application as MyApplication).modifyQuoteUseCase,
                        deleteQuoteUseCase = (application as MyApplication).deleteQuoteUseCase
                    )
                )
                val quoteListUiState =
                    quoteListViewModel.uiState.collectAsStateWithLifecycle().value

                val randomQuoteViewModel: RandomQuoteViewModel = viewModel(
                    factory = RandomQuoteViewModelFactory(
                        getRandomQuoteUseCase = (application as MyApplication).getRandomQuoteUseCase
                    )
                )
                val randomQuoteUiState =
                    randomQuoteViewModel.uiState.collectAsStateWithLifecycle().value

                LifeQuotesApp(
                    windowSize = windowSize.widthSizeClass,
                    foldingDevicePosture = devicePosture,
                    mainUiState = mainUiState,
                    onMainEvent = {
                        mainViewModel.handleIntent(intent = it)
                    },
                    quoteListUiState = quoteListUiState,
                    onQuoteListEvent = {
                        quoteListViewModel.handleIntent(intent = it)
                    },
                    randomQuoteUiState = randomQuoteUiState,
                    onRandomQuoteEvent = {
                        randomQuoteViewModel.handleIntent(intent = it)
                    }
                )
            }
        }
    }
}
