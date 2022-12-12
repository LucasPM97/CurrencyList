package com.lucas.currencylist.domain.extensions

import androidx.annotation.DrawableRes
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.currencylist.R

@DrawableRes
fun ExchangePlatformType.getImage(): Int = when (this) {
    ExchangePlatformType.Buenbit -> R.drawable.buenbit
    ExchangePlatformType.Binance -> R.drawable.binance
    ExchangePlatformType.Ripio -> R.drawable.ripio
    else -> R.drawable.ic_baseline_block_24
}