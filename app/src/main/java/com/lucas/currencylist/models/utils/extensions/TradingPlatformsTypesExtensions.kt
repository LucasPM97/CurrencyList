package com.lucas.currencylist.models.utils.extensions

import androidx.annotation.DrawableRes
import com.lucas.currencylist.R
import com.lucas.currencylist.models.TradingPlatformType


@DrawableRes
fun TradingPlatformType.getImage(): Int = when (this) {
    TradingPlatformType.Buenbit -> R.drawable.buenbit
    TradingPlatformType.Binance -> R.drawable.binance
    else -> R.drawable.ic_baseline_block_24
}

fun TradingPlatformType.getImageName(): String = when (this) {
    TradingPlatformType.Buenbit -> "Buenbit"
    TradingPlatformType.Binance -> "Binance"
    else -> ""
} + " icon"