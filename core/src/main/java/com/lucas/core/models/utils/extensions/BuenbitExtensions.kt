package com.lucas.core.models.utils.extensions

import com.lucas.core.models.buenbit.BuenbitObject
import com.lucas.core.models.CurrencyType
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.buenbit.BuenbitCurrency


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
    exchangeValue = sellingPrice
)

fun BuenbitCurrency.getCurrencyType(currencyName: String): CurrencyType = when (currencyName) {
    "ars" -> CurrencyType.ARS
    "usd" -> CurrencyType.USD
    "dai" -> CurrencyType.DAI
    "btc" -> CurrencyType.BTC
    "eth" -> CurrencyType.ETH
    "bnb" -> CurrencyType.BNB
    else -> CurrencyType.NONE
}