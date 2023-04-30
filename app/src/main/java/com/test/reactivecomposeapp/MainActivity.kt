package com.test.reactivecomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.reactivecomposeapp.data.db.AppDatabase
import com.test.reactivecomposeapp.data.db.Tutor
import com.test.reactivecomposeapp.ui.theme.ReactiveComposeAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch{
            insertDatumIntoDatabase(AppDatabase.getDatabase(applicationContext))
        }
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            ReactiveComposeAppTheme {
                Log.d("MainActivity", "WindowSize : " + windowSize)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

private suspend fun insertDatumIntoDatabase(db: AppDatabase) {
    db.tutorDao().removeAllTutors()
    val tutors = listOf(
        Tutor(name = "홍길동", age = 32, contact = "010-1234-5678", gender = "male"),
        Tutor(name = "김철수", age = 45, contact = "010-9876-54432", gender = "male"),
        Tutor(name = "이영희", age = 35, contact = "010-2345-9876", gender = "female")
    )
    db.tutorDao().insert(tutors);
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReactiveComposeAppTheme {
        Greeting("Android")
    }
}