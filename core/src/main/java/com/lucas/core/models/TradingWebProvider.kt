package com.lucas.core.models

import kotlinx.coroutines.flow.Flow
import java.util.*

data class TradingWebProvider(
    val state: Flow<TradingWebProviderState>,
    val platformType: TradingPlatformType,
    var lastUpdate: Flow<Date>
)

sealed class TradingWebProviderState() {
    class Completed() : TradingWebProviderState()
    class IsLoading() : TradingWebProviderState()
}

enum class TradingPlatformType {
    None,
    Buenbit,
    Binance,
    Ripio
}