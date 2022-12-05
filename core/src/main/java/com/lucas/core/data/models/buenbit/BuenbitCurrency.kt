package com.lucas.core.data.models.buenbit

import com.google.gson.annotations.SerializedName

data class BuenbitCurrency(
    val currency: String,
    @SerializedName("bid_currency") val bidCurrency: String,
    @SerializedName("ask_currency") val askCurrency: String,
    val purchase_price: Double,
    @SerializedName("selling_price") val sellingPrice: Double,
    @SerializedName("market_identifier") val marketIdentifier: String
)