package com.lucas.currencylist.models.ripio

import com.google.gson.annotations.SerializedName

data class RipioCurrency(
    val base: String,
    val quote: String,
    @SerializedName("last_price") val price: Double
)