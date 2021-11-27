package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.currencylist.ui.components.TradingWebList
import com.lucas.currencylist.ui.components.utils.mapToState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun CurrenciesScreen(
    navController: NavController?,
    viewModel: CurrenciesViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    fun favCurrency(currency: CurrencyValue) {
        coroutineScope.launch {
            viewModel.updateFav(currency.currencyId, !currency.fav)
        }
    }


    val platformState = mapToState(
        currenciesMap = viewModel.currencies,
        tradingWebProviders = viewModel.tradingWebProviders
    )

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


@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CurrenciesScreen(navController = null)
}