package com.lucas.core.utils.extensions

import androidx.annotation.DrawableRes
import com.lucas.core.R
import com.lucas.core.models.TradingPlatformType


@DrawableRes
fun TradingPlatformType.getImage(): Int = when (this) {
    TradingPlatformType.Buenbit -> R.drawable.buenbit
    TradingPlatformType.Binance -> R.drawable.binance
    TradingPlatformType.Ripio -> R.drawable.ripio
    else -> R.drawable.ic_baseline_block_24
}

fun TradingPlatformType.getImageName(): String = when (this) {
    TradingPlatformType.Buenbit -> "Buenbit"
    TradingPlatformType.Binance -> "Binance"
    else -> ""
} + " icon"