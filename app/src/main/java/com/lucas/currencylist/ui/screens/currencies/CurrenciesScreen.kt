package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucas.currencylist.ui.components.TradingWebCard

@Composable
fun CurrenciesScreen(
    navController: NavController?,
    viewModel: CurrenciesViewModel = viewModel()
) {
    val buenbitWeb by viewModel.buenbitTradingWeb.observeAsState(null)
    val binanceWeb by viewModel.binanceTradingWeb.observeAsState(null)

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .padding(
                horizontal = 20.dp
            )
    ) {
        buenbitWeb?.let {
            TradingWebCard(
                tradingWeb = it,
                Modifier.padding(bottom = 20.dp)
            )
        }
        binanceWeb?.let {
            TradingWebCard(
                tradingWeb = it
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CurrenciesScreen(navController = null)
}