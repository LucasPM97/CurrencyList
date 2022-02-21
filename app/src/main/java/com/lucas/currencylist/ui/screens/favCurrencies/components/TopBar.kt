package com.lucas.currencylist.ui.screens.favCurrencies.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lucas.currencylist.R

@Composable
fun TopBar() {
    val title = stringResource(id = R.string.favorites_tabBarTitle)
    TopAppBar(
        title = {
            Text(title)
        },
    )
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar()
}