package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.core.models.CurrencyValue
import com.lucas.currencylist.ui.components.TradingWebList
import com.lucas.currencylist.ui.components.utils.mapToState
import kotlinx.coroutines.launch

@Composable
fun FavCurrenciesScreen(
    navController: NavController? = null,
    viewModel: FavCurrenciesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    Scaffold(
        floatingActionButton = {
            NavigateToCurrencyListButton(
                navController = navController
            )
        },
        content = {
            Screen(viewModel, navController)
        }
    )
}

@Composable
private fun Screen(
    viewModel: FavCurrenciesViewModel,
    navController: NavController?
) {
    val coroutineScope = rememberCoroutineScope()

    val platformState = mapToState(
        currenciesMap = viewModel.currencies,
        tradingWebProviders = viewModel.tradingWebProviders
    ).filter { (_, currencies) ->
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
            EmptyFavListScreen(navController)
        } else {
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
private fun EmptyFavListScreen(navController: NavController?) {
    Text(text = "Here will be listed your selected currencies")
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    FavCurrenciesScreen()
}