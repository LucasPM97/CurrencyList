package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun NavigateToCurrencyListButton(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    fun goToCurrencyList() {
        navController?.let {
            it.navigate("currencies")
        }
    }

    FloatingActionButton(
        modifier = modifier,
        onClick = {
            goToCurrencyList()
        },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add currencies"
        )
    }
}

@Composable
@Preview
fun PreviewButton() {
    NavigateToCurrencyListButton()
}