package com.lucas.currencylist.models

import com.google.gson.annotations.SerializedName
import com.lucas.currencylist.models.buenbit.BuenbitCurrency

data class BuenbitCurrencyModel(
    @SerializedName("object") val buenbitObject: BuenbitObject
)

data class BuenbitObject(
    val daiars: BuenbitCurrency,
    val daiusd: BuenbitCurrency,
    val btcdai: BuenbitCurrency,
    val ethdai: BuenbitCurrency
)