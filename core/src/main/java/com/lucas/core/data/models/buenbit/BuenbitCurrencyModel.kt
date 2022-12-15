package com.lucas.core.data.models.buenbit

import com.google.gson.annotations.SerializedName

data class BuenbitCurrencyModel(
    @SerializedName("object") val buenbitObject: BuenbitObject
)

data class BuenbitObject(
    val daiars: BuenbitExchangeValue,
    val daiusd: BuenbitExchangeValue,
    val btcdai: BuenbitExchangeValue,
    val ethdai: BuenbitExchangeValue,
    val bnbdai: BuenbitExchangeValue
)