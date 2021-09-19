package com.lucas.currencylist.models.binance

import com.google.gson.annotations.SerializedName
import com.lucas.currencylist.models.buenbit.BuenbitCurrency

data class BinanceCurrencyModel(
    val data: List<BinanceCurrency>
)