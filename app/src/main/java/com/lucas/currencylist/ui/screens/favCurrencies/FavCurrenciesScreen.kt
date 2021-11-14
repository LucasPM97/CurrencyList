package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.core.models.CurrencyValue
import com.lucas.currencylist.ui.components.TradingWebList
import kotlinx.coroutines.launch

@Composable
fun FavCurrenciesScreen(
    navController: NavController?,
    viewModel: FavCurrenciesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    val platformState = viewModel.currencies.map { (platform, currenciesFlow) ->
        val currencies by currenciesFlow.collectAsState(initial = emptyList())

        val providerState = viewModel.tradingWebProviders.first {
            it.platformType == platform
        }

        providerState to currencies
    }.filter { (_, currencies) ->
        currencies.isNotEmpty()
    }

    fun favCurrency(currency: CurrencyValue) {
        coroutineScope.launch {
            viewModel.updateFav(currency.currencyId, !currency.fav)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (platformState.isEmpty()) {
            EmptyFavListMessage()
        }
        GoToCurrencyListButton(
            navController,
            modifier = Modifier
                .padding(
                    top = 20.dp,
                    bottom = 20.dp
                )
        )
        if (platformState.isNotEmpty()) {
            TradingWebList(
                platformState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp),
                itemFavOnClick = {
                    favCurrency(it)
                }
            )
        }
    }
}

@Composable
private fun EmptyFavListMessage() {
    Text(text = "Here will be listed your selected currencies")
}

@Composable
fun GoToCurrencyListButton(
    navController: NavController?,
    modifier: Modifier = Modifier
) {
    fun goToCurrencyList() {
        navController?.let {
            it.navigate("currencies")
        }
    }
    Button(
        modifier = modifier,
        onClick = {
            goToCurrencyList()
        }
    ) {
        Text("All currencies")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    FavCurrenciesScreen(navController = null)
}