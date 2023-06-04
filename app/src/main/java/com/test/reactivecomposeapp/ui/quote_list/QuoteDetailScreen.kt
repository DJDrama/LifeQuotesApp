package com.test.reactivecomposeapp.ui.quote_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.domain.model.Quote

@Composable
fun QuoteDetailScreen(
    modifier: Modifier = Modifier,
    quote: Quote,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        content = {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(16.dp),
            ) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = quote.date,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = quote.quote,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = quote.author,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = quote.description,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    )
}

@Composable
@Preview
fun QuoteDetailScreenPreview() {
    QuoteDetailScreen(
        quote = Quote(1, "Hello World", "World", "description", "2000-01-01", true)
    )
}