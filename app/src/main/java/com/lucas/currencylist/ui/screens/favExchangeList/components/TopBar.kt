package com.lucas.currencylist.ui.screens.favExchangeList.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lucas.currencylist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val title = stringResource(id = R.string.favScreen_tabBarTitle)
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