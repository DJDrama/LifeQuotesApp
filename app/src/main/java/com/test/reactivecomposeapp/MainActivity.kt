package com.test.reactivecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.reactivecomposeapp.data.db.StudentDao
import com.test.reactivecomposeapp.data.repository.StudentRepositoryImpl
import com.test.reactivecomposeapp.ui.student_screen.StudentViewModel
import com.test.reactivecomposeapp.ui.student_screen.StudentViewModelFactory
import com.test.reactivecomposeapp.ui.student_screen.StudentsScreen
import com.test.reactivecomposeapp.ui.theme.ReactiveComposeAppTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReactiveComposeAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val studentViewModel: StudentViewModel = viewModel(
                        factory = StudentViewModelFactory(repository = (application as MyApplication).repository)
                    )
                    val stateValue = studentViewModel.uiState.collectAsStateWithLifecycle().value
                    StudentsScreen(
                        state = stateValue,
                        onEvent = studentViewModel::handleIntent
                    )
                }
            }
        }
    }
}
