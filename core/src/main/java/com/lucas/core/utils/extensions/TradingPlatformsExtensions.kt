package com.lucas.core.utils.extensions

import android.content.Context
import androidx.annotation.DrawableRes
import com.lucas.core.R
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.core.models.TradingWebProvider
import com.lucas.core.utils.helpers.DateHelper
import java.util.*

fun Date.lastUpdateText(context: Context): String {
    val currentDate = DateHelper.currentDate()
    val currentDetailedDate = currentDate.toDetailedDate()
    val lastUpdateDetailedDate = this.toDetailedDate()

    if (lastUpdateDetailedDate.day == currentDetailedDate.day) {
        return this.toString("HH:mm")
    } else
        if (currentDetailedDate.day == (lastUpdateDetailedDate.day - 1)) {
            return context.getString(R.string.yesterday) + this.toString("HH:mm")
        }

    return this.toString("MMM dd")
}

@DrawableRes
fun TradingPlatformType.getImage(): Int = when (this) {
    TradingPlatformType.Buenbit -> R.drawable.buenbit
    TradingPlatformType.Binance -> R.drawable.binance
    TradingPlatformType.Ripio -> R.drawable.ripio
    else -> R.drawable.ic_baseline_block_24
}

fun TradingPlatformType.getName(): String = when (this) {
    TradingPlatformType.Buenbit -> "Buenbit"
    TradingPlatformType.Binance -> "Binance"
    TradingPlatformType.Ripio -> "Ripio"
    else -> ""
}