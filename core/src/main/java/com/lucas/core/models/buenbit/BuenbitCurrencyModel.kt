package com.lucas.core.models.buenbit

import com.google.gson.annotations.SerializedName

data class BuenbitCurrencyModel(
    @SerializedName("object") val buenbitObject: BuenbitObject
)

data class BuenbitObject(
    val daiars: BuenbitCurrency,
    val daiusd: BuenbitCurrency,
    val btcdai: BuenbitCurrency,
    val ethdai: BuenbitCurrency,
    val bnbdai: BuenbitCurrency
)