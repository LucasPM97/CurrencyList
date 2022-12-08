package com.lucas.core.data.models

import kotlinx.coroutines.flow.Flow
import java.util.*

data class TradingWebProvider(
    val platformType: ExchangePlatformType,
    var lastUpdate: Flow<Date>
)

sealed class TradingWebProviderState() {
    class Completed() : TradingWebProviderState()
    class IsLoading() : TradingWebProviderState()
}

enum class ExchangePlatformType {
    None,
    Buenbit,
    Binance,
    Ripio
}