package com.test.reactivecomposeapp.ui.student_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.test.reactivecomposeapp.domain.model.Student


@Composable
fun StudentRow(
    modifier: Modifier = Modifier,
    student: Student,
    onClick: (Student) -> Unit,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClick(student)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = modifier.weight(1f), text = student.name, textAlign = TextAlign.Center)
        Text(
            modifier = modifier.weight(1f),
            text = student.age.toString(),
            textAlign = TextAlign.Center
        )
        Text(modifier = modifier.weight(1f), text = student.gender, textAlign = TextAlign.Center)
        Text(modifier = modifier.weight(1f), text = student.contact, textAlign = TextAlign.Center)
    }
}