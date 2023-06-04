package com.test.reactivecomposeapp.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.More
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.test.reactivecomposeapp.R

@Composable
fun LifeQuotesNavigationRail(
    modifier: Modifier = Modifier,
    onDrawerClicked: () -> Unit = {},
) {
    NavigationRail(modifier = modifier.fillMaxHeight()) {
        NavigationRailItem(
            selected = false,
            onClick = onDrawerClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = stringResource(id = R.string.navigation_drawer)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = { } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.Icecream,
                    contentDescription = stringResource(id = R.string.life_quotes_list)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = { } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.random_quote)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = { } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.More,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = { } ,
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.more)
                )
            }
        )
    }
}