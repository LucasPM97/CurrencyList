package com.lucas.core.models

import androidx.lifecycle.LiveData

data class TradingWebProvider(
    val state: LiveData<TradingWebProviderState>,
    val platformType: TradingPlatformType
)

sealed class TradingWebProviderState() {
    data class Completed(val tradingWeb: TradingWeb) : TradingWebProviderState()
    class IsLoading() : TradingWebProviderState()
}