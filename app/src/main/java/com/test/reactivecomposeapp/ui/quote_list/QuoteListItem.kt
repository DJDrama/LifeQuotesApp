package com.test.reactivecomposeapp.ui.quote_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.R
import com.test.reactivecomposeapp.domain.model.Quote

@Composable
fun QuoteListItem(
    modifier: Modifier = Modifier,
    quote: Quote,
    onClickFavorite: (Quote) -> Unit,
    onClickQuoteRow: (Quote) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onClickQuoteRow(quote)
                    }
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = quote.quote,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    Text(
                        text = quote.author,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    Text(
                        text = quote.date,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                IconButton(
                    modifier = modifier.wrapContentSize(),
                    onClick = {
                        onClickFavorite(quote)
                    }
                ) {
                    Icon(
                        imageVector = if (quote.favorite)
                            Icons.Default.Star
                        else Icons.Default.StarBorder,
                        tint = if (quote.favorite) Color.Yellow else Color.Gray,
                        contentDescription = stringResource(id = R.string.favorite)
                    )
                }
            }
        }
    )
}
