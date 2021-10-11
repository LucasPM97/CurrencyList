package com.lucas.core.models

data class TradingWeb(
    val currecies: List<CurrencyValue> = emptyList(),
    val platformType: TradingPlatformType
)

enum class TradingPlatformType{
    Buenbit,
    Binance,
    Ripio
}