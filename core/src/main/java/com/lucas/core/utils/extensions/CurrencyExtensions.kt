package com.lucas.core.utils.extensions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lucas.core.R
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.utils.helpers.DateHelper
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

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

@StringRes
fun CurrencyType.getImageName(): Int = when (this) {
    CurrencyType.DAI -> R.string.dai_icon_description
    CurrencyType.BTC -> R.string.btc_icon_description
    CurrencyType.ETH -> R.string.eth_icon_description
    CurrencyType.ARS -> R.string.ars_icon_description
    CurrencyType.USD -> R.string.usd_icon_description
    CurrencyType.USDC -> R.string.usdc_icon_description
    CurrencyType.BNB -> R.string.bnb_icon_description
    else -> 0
}