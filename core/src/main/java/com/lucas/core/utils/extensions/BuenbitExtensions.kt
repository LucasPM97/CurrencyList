package com.lucas.core.utils.extensions

import com.lucas.core.data.models.buenbit.BuenbitObject
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.TradingPlatformType
import com.lucas.core.data.models.buenbit.BuenbitCurrency


fun BuenbitObject.toCurrencyList(): List<CurrencyValue> = listOf(
    daiars.toCurrencyValue(),
    daiusd.toCurrencyValue(),
    btcdai.toCurrencyValue(),
    ethdai.toCurrencyValue(),
    bnbdai.toCurrencyValue()
)

fun BuenbitCurrency.toCurrencyValue(): CurrencyValue = CurrencyValue(
    exchangeFrom = this.getCurrencyType(currency),
    exchangeTo = this.getCurrencyType(bidCurrency),
    exchangeValue = sellingPrice,
    platform = com.lucas.core.data.models.TradingPlatformType.Buenbit
)

fun BuenbitCurrency.getCurrencyType(currencyName: String): CurrencyType = when (currencyName) {
    "ars" -> com.lucas.core.data.models.CurrencyType.ARS
    "usd" -> com.lucas.core.data.models.CurrencyType.USD
    "dai" -> com.lucas.core.data.models.CurrencyType.DAI
    "btc" -> com.lucas.core.data.models.CurrencyType.BTC
    "eth" -> com.lucas.core.data.models.CurrencyType.ETH
    "bnb" -> com.lucas.core.data.models.CurrencyType.BNB
    else -> com.lucas.core.data.models.CurrencyType.NONE
}