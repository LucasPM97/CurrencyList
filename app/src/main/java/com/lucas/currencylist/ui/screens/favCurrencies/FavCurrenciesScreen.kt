package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.lucas.core.data.models.ExchangeValue
import com.lucas.currencylist.ui.screens.favCurrencies.components.FavoriteList
import com.lucas.currencylist.ui.screens.favCurrencies.components.NavigateToCurrencyListButton
import com.lucas.currencylist.ui.screens.favCurrencies.components.TopBar
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
            Column(modifier = Modifier.padding(it)) {
                Screen(viewModel)
            }
        }
    )
}

@Composable
private fun Screen(
    viewModel: FavCurrenciesViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    val platformsState by viewModel.currencies.collectAsState(null)

    fun favCurrency(currency: ExchangeValue) {
        coroutineScope.launch {
            viewModel.updateFav(currency.currencyId, !currency.fav)
        }
    }

    platformsState?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val anyExchangeValuesStored = it.any {
                it.exchangeValues.any()
            }

            if (!anyExchangeValuesStored) {
                EmptyFavListMessage()
            } else {
                FavoriteList(
                    platformsState = it,
                    itemFavOnClick = {
                        favCurrency(it)
                    }
                )
            }
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