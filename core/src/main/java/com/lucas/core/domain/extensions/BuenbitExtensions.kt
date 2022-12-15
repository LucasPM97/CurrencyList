package com.lucas.core.domain.extensions

import com.lucas.core.data.models.buenbit.BuenbitObject
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.buenbit.BuenbitExchangeValue


fun BuenbitObject.toExchangeValueList(): List<ExchangeValue> = listOf(
    daiars.toExchangeValue(),
    daiusd.toExchangeValue(),
    btcdai.toExchangeValue(),
    ethdai.toExchangeValue(),
    bnbdai.toExchangeValue()
)

fun BuenbitExchangeValue.toExchangeValue(): ExchangeValue = ExchangeValue(
    exchangeFrom = this.getCurrencyType(currency),
    exchangeTo = this.getCurrencyType(bidCurrency),
    exchangeValue = sellingPrice,
    platform = ExchangePlatformType.Buenbit
)

fun BuenbitExchangeValue.getCurrencyType(currencyName: String): CurrencyType = when (currencyName) {
    "ars" -> CurrencyType.ARS
    "usd" -> CurrencyType.USD
    "dai" -> CurrencyType.DAI
    "btc" -> CurrencyType.BTC
    "eth" -> CurrencyType.ETH
    "bnb" -> CurrencyType.BNB
    else -> CurrencyType.NONE
}