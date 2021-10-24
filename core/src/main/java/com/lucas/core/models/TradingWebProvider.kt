package com.lucas.core.models

import androidx.lifecycle.LiveData

data class TradingWebProvider(
    val state: LiveData<TradingWebProviderState>,
    val platformType: TradingPlatformType
)

sealed class TradingWebProviderState() {
    class Completed() : TradingWebProviderState()
    class IsLoading() : TradingWebProviderState()
}

enum class TradingPlatformType{
    None,
    Buenbit,
    Binance,
    Ripio
}