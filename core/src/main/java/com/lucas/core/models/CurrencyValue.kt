package com.lucas.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucas.core.utils.extensions.getName
import com.lucas.core.utils.extensions.roundString

data class CurrencyValue(
    val exchangeValue: Double,
    val exchangeFrom: CurrencyType,
    val exchangeTo: CurrencyType
) {
    val exchangeTitle = "${exchangeFrom.getName()} / ${exchangeTo.getName()}"

    val exchangeString = "${exchangeValue.roundString()} ${exchangeFrom.getName()}"
}

enum class CurrencyType {
    NONE,
    ARS,
    USD,
    USDC,
    DAI,
    BTC,
    ETH,
    BNB
}