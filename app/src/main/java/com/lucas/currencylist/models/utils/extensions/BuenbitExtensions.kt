package com.lucas.currencylist.models.utils.extensions

import com.lucas.currencylist.models.BuenbitObject
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.buenbit.BuenbitCurrency


fun BuenbitObject.toCurrencyList(): List<CurrencyValue> = listOf(
    daiars.toCurrencyValue(),
    daiusd.toCurrencyValue(),
    btcdai.toCurrencyValue(),
    ethdai.toCurrencyValue(),
    bnbdai.toCurrencyValue()
)

fun BuenbitCurrency.toCurrencyValue(): CurrencyValue = CurrencyValue(
    exchangeFrom = currency.toCurrencyType(),
    exchangeTo = bidCurrency.toCurrencyType(),
    exchangeValue = sellingPrice
)