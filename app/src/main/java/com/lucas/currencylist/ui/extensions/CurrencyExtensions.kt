package com.lucas.currencylist.ui.extensions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lucas.core.data.models.CurrencyType
import com.lucas.currencylist.R


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