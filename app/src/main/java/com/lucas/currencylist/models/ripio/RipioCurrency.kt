package com.lucas.currencylist.models.ripio

data class RipioCurrency(
    val base: String,
    val quote: String,
    val bid: Float
)