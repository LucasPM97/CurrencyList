package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.lucas.core.models.CurrencyValue
import com.lucas.currencylist.ui.components.utils.mapToState
import kotlinx.coroutines.launch

@Composable
fun FavCurrenciesScreen(
    navController: NavController? = null,
    viewModel: FavCurrenciesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    Scaffold(
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            NavigateToCurrencyListButton(
                navController = navController
            )
        },
        content = {
            Screen(viewModel)
        }
    )
}

@Composable
private fun Screen(
    viewModel: FavCurrenciesViewModel
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
            EmptyFavListMessage()
        } else {
            FavoriteList(
                platformState,
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

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    FavCurrenciesScreen()
}