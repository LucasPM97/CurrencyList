package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.TradingPlatformType
import com.lucas.core.data.models.TradingWebProvider

val itemsSpace = 10.dp

@Composable
fun TradingWebList(
    platformState: List<Pair<TradingWebProvider, List<CurrencyValue>>>,
    modifier: Modifier = Modifier,
    itemFavOnClick: (currency: CurrencyValue) -> Unit
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        platformState.forEach { (providerState, currencies) ->
            RenderTradingWeb(
                tradingWebProvider = providerState,
                platformType = providerState.platformType,
                currencies = currencies,
                itemFavOnClick = { currencyId ->
                    val currency = currencies.first {
                        it.currencyId == currencyId
                    }
                    itemFavOnClick(currency)
                }
            )
        }
    }
}


@Composable
private fun RenderTradingWeb(
    tradingWebProvider: TradingWebProvider,
    platformType: TradingPlatformType,
    currencies: List<CurrencyValue>,
    modifier: Modifier = Modifier,
    itemFavOnClick: (currencyId: String) -> Unit
) {
    val tradingWebState by tradingWebProvider.state.collectAsState(null)
    val lastUpdate by tradingWebProvider.lastUpdate.collectAsState(null)

    tradingWebState?.let {
        TradingWebCard(
            platformType,
            tradingWebState = it,
            lastUpdate = lastUpdate,
            currencyList = currencies,
            itemFavOnClick = itemFavOnClick,
            modifier = modifier.padding(
                top = itemsSpace,
                bottom = itemsSpace
            )
        )
    }
}