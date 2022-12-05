package com.lucas.core.utils.extensions

import android.content.Context
import androidx.annotation.DrawableRes
import com.lucas.core.R
import com.lucas.core.data.models.ExchangePlatformType
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
fun ExchangePlatformType.getImage(): Int = when (this) {
    ExchangePlatformType.Buenbit -> R.drawable.buenbit
    ExchangePlatformType.Binance -> R.drawable.binance
    ExchangePlatformType.Ripio -> R.drawable.ripio
    else -> R.drawable.ic_baseline_block_24
}

fun ExchangePlatformType.getName(): String = when (this) {
    ExchangePlatformType.Buenbit -> "Buenbit"
    ExchangePlatformType.Binance -> "Binance"
    ExchangePlatformType.Ripio -> "Ripio"
    else -> ""
}