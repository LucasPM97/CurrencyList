package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucas.core.data.models.CurrencyValue
import com.lucas.currencylist.ui.components.TradingWebList
import com.lucas.currencylist.ui.screens.currencies.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun CurrenciesScreen(
    navController: NavController?,
    viewModel: CurrenciesViewModel = viewModel()
) {
    fun onBack() {
        navController?.let {
            it.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopBar(onBack = { onBack() })
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Screen(viewModel)
            }
        }
    )
}

@Composable
private fun Screen(viewModel: CurrenciesViewModel) {
    val coroutineScope = rememberCoroutineScope()

    fun favCurrency(currency: CurrencyValue) {
        coroutineScope.launch {
            viewModel.updateFav(currency.currencyId, !currency.fav)
        }
    }

    val platformsState by viewModel.currencies.collectAsState(initial = null)

    platformsState?.let {
        TradingWebList(
            platformState = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            itemFavOnClick = {
                favCurrency(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CurrenciesScreen(navController = null)
}