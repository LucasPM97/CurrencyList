package com.lucas.core.models.utils.extensions

import androidx.annotation.DrawableRes
import com.lucas.core.R
import com.lucas.core.models.CurrencyType


@DrawableRes
fun CurrencyType.getImage(): Int = when (this) {
    CurrencyType.DAI -> R.drawable.ic_dai
    CurrencyType.BTC -> R.drawable.ic_bitcoin
    CurrencyType.ETH -> R.drawable.ic_ethereum
    CurrencyType.ARS -> R.drawable.ic_arspeso
    CurrencyType.USD -> R.drawable.ic_dollar
    CurrencyType.USDC -> R.drawable.ic_dollar
    CurrencyType.BNB -> R.drawable.ic_bnbcoin
    else -> R.drawable.ic_baseline_block_24
}

fun CurrencyType.getName(): String = when (this) {
    CurrencyType.DAI -> "DAI"
    CurrencyType.BTC -> "BTC"
    CurrencyType.ETH -> "ETH"
    CurrencyType.ARS -> "ARS"
    CurrencyType.USD -> "USD"
    CurrencyType.USDC -> "USDC"
    CurrencyType.BNB -> "BNB"
    else -> ""
}

fun CurrencyType.getImageName(): String = when (this) {
    CurrencyType.DAI -> "DAI"
    CurrencyType.BTC -> "Bitcoin"
    CurrencyType.ETH -> "Ethereum"
    CurrencyType.ARS -> "Argentina peso"
    CurrencyType.USD -> "Dollar"
    CurrencyType.USDC -> "USD Coin"
    CurrencyType.BNB -> "Binance Coin"
    else -> ""
} + " icon"