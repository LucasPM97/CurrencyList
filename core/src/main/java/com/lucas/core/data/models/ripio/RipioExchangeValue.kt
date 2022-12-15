package com.lucas.core.data.models.ripio

import com.google.gson.annotations.SerializedName

data class RipioExchangeValue(
    val base: String,
    val quote: String,
    @SerializedName("last_price") val price: Double
)