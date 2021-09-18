package com.lucas.currencylist.models

import com.lucas.currencylist.models.utils.extensions.getName

data class CurrencyValue(
    val exchangeValue: Float,
    val exchangeFrom: CurrencyType,
    val exchangeTo: CurrencyType
) {
    val exchangeTitle = "${exchangeFrom.getName()} / ${exchangeTo.getName()}"

    val exchangeString = "$exchangeValue ${exchangeFrom.getName()}"
}

enum class CurrencyType {
    NONE,
    ARS,
    USD,
    DAI,
    BTC,
    ETH,
    BNB
}