package com.lucas.currencylist.ui.components.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.TradingPlatformType
import com.lucas.core.data.models.TradingWebProvider
import kotlinx.coroutines.flow.Flow

@Composable
fun mapToState(
    currenciesMap: Map<TradingPlatformType, Flow<List<CurrencyValue>>>,
    tradingWebProviders: List<TradingWebProvider>
) =
    currenciesMap.map { (platform, currenciesFlow) ->
        val currencies by currenciesFlow.collectAsState(initial = emptyList())

        val providerState = tradingWebProviders.first {
            it.platformType == platform
        }

        providerState to currencies
    }
