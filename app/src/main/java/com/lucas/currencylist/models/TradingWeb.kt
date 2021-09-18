package com.lucas.currencylist.models

import androidx.annotation.DrawableRes

data class TradingWeb(
    val currecies: List<CurrencyValue> = emptyList(),
    val platformType: TradingPlatformType
)

enum class TradingPlatformType{
    Buenbit
}