package com.lucas.currencylist.models

import androidx.annotation.DrawableRes
import com.lucas.currencylist.R

data class CurrencyValue(
    val exchangeValue: Float,
    val exchangeFrom: CurrencyType,
    val exchangeTo: CurrencyType
) {
    val exchangeTitle = "${exchangeFrom.getName()} / ${exchangeFrom.getName()}"
}

enum class CurrencyType {
    ARS,
    USD,
    DAI,
    BTC,
    ETH
}

@DrawableRes
fun CurrencyType.getImage(): Int = when (this) {
    CurrencyType.DAI -> R.drawable.ic_dai
    CurrencyType.BTC -> R.drawable.ic_bitcoin
    CurrencyType.ETH -> R.drawable.ic_ethereum
    CurrencyType.ARS -> R.drawable.ic_arspeso
    CurrencyType.USD -> R.drawable.ic_dollar
    else -> R.drawable.ic_baseline_block_24
}

fun CurrencyType.getName(): String = when (this) {
    CurrencyType.DAI -> "DAI"
    CurrencyType.BTC -> "BTC"
    CurrencyType.ETH -> "ETH"
    CurrencyType.ARS -> "ARS"
    CurrencyType.USD -> "U" + '$' + "D"
    else -> ""
}

fun CurrencyType.getImageName(): String = when (this) {
    CurrencyType.DAI -> "DAI"
    CurrencyType.BTC -> "Bitcoin"
    CurrencyType.ETH -> "Ethereum"
    CurrencyType.ARS -> "Argentina peso"
    CurrencyType.USD -> "Dollar"
    else -> ""
} + " icon"