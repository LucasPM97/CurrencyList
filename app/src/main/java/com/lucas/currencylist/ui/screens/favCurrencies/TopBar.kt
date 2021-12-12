package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text("Your list")
        },
    )
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar()
}