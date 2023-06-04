package com.test.reactivecomposeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.test.reactivecomposeapp.R
import com.test.reactivecomposeapp.domain.model.Quote
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AddModifyQuoteDialog(
    quote: Quote? = null,
    onCancel: () -> Unit,
    onDeleteQuoteItem: (Int) -> Unit,
    onModifyComplete: (Quote) -> Unit,
    onAddComplete: (Quote) -> Unit
) {
    Dialog(onDismissRequest = { },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        AddModifyQuoteUiContent(
            quote = quote,
            onCancel = onCancel,
            onModifyComplete = onModifyComplete,
            onDeleteQuoteItem = onDeleteQuoteItem,
            onAddComplete = onAddComplete
        )
    }
}

@Composable
private fun AddModifyQuoteUiContent(
    modifier: Modifier = Modifier,
    quote: Quote? = null,
    onCancel: () -> Unit,
    onDeleteQuoteItem: (Int) -> Unit,
    onModifyComplete: (Quote) -> Unit,
    onAddComplete: (Quote) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val quoteString = remember { mutableStateOf(quote?.quote ?: "") }
    val author = remember { mutableStateOf(quote?.author ?: "") }
    val description = remember { mutableStateOf(quote?.description ?: "") }
    Card(
        modifier = modifier.wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 12.dp, horizontal = 12.dp)
        ) {
            Text(
                text = if (quote != null) stringResource(R.string.modify_quote) else stringResource(
                    R.string.add_quote
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.quote), modifier = modifier.weight(0.35f))
                Spacer(modifier = modifier.padding(horizontal = 8.dp))
                OutlinedTextField(
                    modifier = modifier.weight(0.65f),
                    value = quoteString.value,
                    onValueChange = {
                        quoteString.value = it
                    },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Start
                    ),
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
            }
            Spacer(modifier = modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.author), modifier = modifier.weight(0.35f))
                Spacer(modifier = modifier.padding(horizontal = 8.dp))
                OutlinedTextField(
                    modifier = modifier.weight(0.65f),
                    value = author.value.toString(),
                    onValueChange = {
                        author.value = it
                    },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Start
                    ),
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
            }
            Spacer(modifier = modifier.padding(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.description), modifier = modifier.weight(0.35f))
                Spacer(modifier = modifier.padding(horizontal = 8.dp))
                OutlinedTextField(
                    modifier = modifier.weight(0.65f),
                    value = description.value.toString(),
                    onValueChange = {
                        description.value = it
                    },
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Start
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.clearFocus() }
                    )
                )
            }
            Spacer(modifier = modifier.padding(8.dp))
            Row {
                Button(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {
                        if (quote != null) {
                            onDeleteQuoteItem(quote.id!!)
                        } else {
                            onCancel()
                        }
                    }
                ) {
                    Text(
                        text = if (quote != null) {
                            stringResource(R.string.delete)
                        } else {
                            stringResource(R.string.cancel)
                        },
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = modifier.padding(4.dp))
                Button(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .weight(0.5f),
                    onClick = {
                        if (quote != null) {
                            onModifyComplete(
                                quote.copy(
                                    quote = quoteString.value,
                                    author = author.value,
                                    description = description.value
                                )
                            )
                        } else
                            onAddComplete(
                                Quote(
                                    quote = quoteString.value,
                                    author = author.value,
                                    description = description.value,
                                    date = LocalDateTime.now()
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                    favorite = false
                                )
                            )
                    }
                ) {
                    Text(
                        text = if (quote != null) {
                            stringResource(R.string.modify)
                        } else {
                            stringResource(R.string.add)
                        },
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}