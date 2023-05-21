package com.test.reactivecomposeapp.ui.student_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.ui.student_screen.components.StudentRow

@Composable
fun StudentsScreen(
    modifier: Modifier = Modifier,
    state: StudentUiState,
    onEvent: (StudentIntent) -> Unit
) {
    state.studentDetail?.let {
        StudentDetailScreen(student = it, onEvent = onEvent)
    } ?:
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                onEvent(StudentIntent.InsertNewStudent)
            }) {
                Text(text = "Insert New Student")
            }
        }
        Spacer(modifier = modifier.padding(vertical = 4.dp))
        Row(modifier = modifier.fillMaxWidth()) {
            Text(modifier = modifier.weight(1f), text = "Name", textAlign = TextAlign.Center)
            Text(modifier = modifier.weight(1f), text = "Age", textAlign = TextAlign.Center)
            Text(modifier = modifier.weight(1f), text = "Gender", textAlign = TextAlign.Center)
            Text(modifier = modifier.weight(1f), text = "Contact", textAlign = TextAlign.Center)
        }
        Spacer(modifier = modifier.padding(vertical = 4.dp))
        Divider(thickness = 1.dp, color = Color.Gray)
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.students) { student ->
                StudentRow(student = student) { selectedStudent ->
                    onEvent(StudentIntent.ShowStudent(selectedStudent))
                }
            }
        }
    }
}

@Preview
@Composable
fun StudentsScreenPreview() {
    StudentsScreen(state = StudentUiState()) {

    }
}