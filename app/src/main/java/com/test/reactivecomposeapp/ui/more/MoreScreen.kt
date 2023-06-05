package com.test.reactivecomposeapp.ui.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.reactivecomposeapp.R
import com.test.reactivecomposeapp.ui.AppBar

@Composable
fun MoreScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = "More",
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding() + 16.dp)
                .padding(horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "버전 정보"
                )
                Text(
                    text = "v1.0.0"
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "엘리스 아카데미"
                )
                Text(
                    text = "elice@elice.com"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.align(Alignment.End),
                painter = painterResource(id = R.drawable.elice2),
                contentDescription = ""
            )
        }
    }
}