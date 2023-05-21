package com.test.reactivecomposeapp.ui.student_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.domain.model.Student

@Composable
fun StudentDetailScreen(
    modifier: Modifier = Modifier,
    student: Student,
    onEvent: (StudentIntent) -> Unit
) {
    Column {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = {
                    onEvent(StudentIntent.ClearStudentDetailInformation)
                }
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
            Row {
                Button(onClick = {
                    onEvent(StudentIntent.UpdateStudent(student))
                }) {
                    Text(text = "Update")
                }
                Spacer(modifier = modifier.padding(4.dp))
                Button(onClick = {
                    onEvent(StudentIntent.DeleteStudent(student))
                }) {
                    Text(text = "Delete")
                }
            }
        }
        Spacer(modifier = modifier.padding(vertical = 8.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            value = student.name, onValueChange = {
                onEvent(StudentIntent.OnChangeName(name = it))
            })
        Spacer(modifier = modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = student.age.toString(),
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                onEvent(StudentIntent.OnChangeAge(age = it))
            })
        Spacer(modifier = modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = student.contact,
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                onEvent(StudentIntent.OnChangeContact(contact = it))
            })
        Spacer(modifier = modifier.padding(vertical = 4.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = student.description,
            onValueChange = {
                onEvent(StudentIntent.OnChangeDescription(description = it))
            })
        Spacer(modifier = modifier.padding(vertical = 4.dp))
    }
}