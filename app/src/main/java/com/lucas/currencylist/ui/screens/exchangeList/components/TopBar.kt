package com.lucas.currencylist.ui.screens.exchangeList.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lucas.currencylist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.currencies_backButton_description),
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar(
        onBack = {}
    )
}