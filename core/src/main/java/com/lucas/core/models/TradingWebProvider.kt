package com.lucas.core.models

import androidx.lifecycle.LiveData

data class TradingWebProvider(
    val state: LiveData<TradingWebProviderState>,
    val platformType: TradingPlatformType
)

sealed class TradingWebProviderState() {
    data class Completed(val currencies: List<CurrencyValue>) : TradingWebProviderState()
    data class IsLoading(val currencies: List<CurrencyValue>) : TradingWebProviderState()
}

enum class TradingPlatformType{
    None,
    Buenbit,
    Binance,
    Ripio
}