package com.lucas.currencylist.models

import com.lucas.currencylist.models.utils.extensions.getName
import com.lucas.currencylist.models.utils.extensions.getPriceFormat

data class CurrencyValue(
    val exchangeValue: Float,
    val exchangeFrom: CurrencyType,
    val exchangeTo: CurrencyType
) {
    val exchangeTitle = "${exchangeFrom.getName()} / ${exchangeTo.getName()}"

    val exchangeString = exchangeFrom.getPriceFormat(exchangeValue)
}

enum class CurrencyType {
    NONE,
    ARS,
    USD,
    DAI,
    BTC,
    ETH
}