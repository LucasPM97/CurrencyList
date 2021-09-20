package com.lucas.currencylist.models

import com.lucas.currencylist.models.utils.extensions.getName
import com.lucas.currencylist.models.utils.extensions.roundString

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