package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CurrenciesScreen(
    navController: NavController?,
    viewModel: CurrenciesViewModel = viewModel()
) {
    /*ErrorScreen()*/
    CurrencyList(viewModel.currencies)
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CurrenciesScreen(navController = null)
}