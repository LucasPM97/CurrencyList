package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucas.currencylist.models.TradingWeb
import com.lucas.currencylist.models.TradingWebProvider
import com.lucas.currencylist.ui.components.TradingWebCard

val bottomSpace = 20.dp

@Composable
fun CurrenciesScreen(
    navController: NavController?,
    viewModel: CurrenciesViewModel = viewModel()
) {
    val providers = viewModel.tradingWebProviders

    Column(
        Modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
            )
            .verticalScroll(rememberScrollState())

    ) {
        providers.forEach {
            RenderTradingWeb(it)
        }
    }
}

@Composable
fun RenderTradingWeb(
    tradingWebProvider: TradingWebProvider,
    modifier: Modifier = Modifier
) {
    val tradingWeb by tradingWebProvider.state
        .asLiveData()
        .observeAsState(initial = null)

    tradingWeb?.let {
        TradingWebCard(
            tradingWeb = it,
            modifier.padding(bottom = bottomSpace)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CurrenciesScreen(navController = null)
}