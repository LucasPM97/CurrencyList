package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.core.models.TradingWebProvider
import com.lucas.currencylist.ui.screens.currencies.itemsSpace

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
    val tradingWebState by tradingWebProvider.state.observeAsState()

    tradingWebState?.let {
        TradingWebCard(
            platformType,
            tradingWebState = it,
            currencyList = currencies,
            itemFavOnClick = itemFavOnClick,
            modifier.padding(top = itemsSpace)
        )
    }
}