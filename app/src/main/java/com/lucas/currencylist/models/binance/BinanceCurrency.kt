package com.lucas.currencylist.models.binance

import com.google.gson.annotations.SerializedName

data class BinanceCurrency(
    val name: String,
    val price: Float,
)