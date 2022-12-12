package com.lucas.core.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.lucas.core.domain.extensions.getName
import com.lucas.core.domain.extensions.roundString

@Entity(tableName = "exchange_table")
data class ExchangeValue(
    var platform: ExchangePlatformType = ExchangePlatformType.None,
    var exchangeValue: Double,
    var exchangeFrom: CurrencyType = CurrencyType.NONE,
    var exchangeTo: CurrencyType = CurrencyType.NONE,
    var fav: Boolean = false,
    @PrimaryKey
    val currencyId: String = "${platform.getName()}_${exchangeFrom.getName()}_${exchangeTo.getName()}",
) {
    @Ignore
    var exchangeTitle = "${exchangeFrom.getName()} / ${exchangeTo.getName()}"

    @Ignore
    var exchangeString = "${exchangeValue.roundString()} ${exchangeFrom.getName()}"
}

enum class CurrencyType {
    NONE,
    ARS,
    USD,
    USDC,
    DAI,
    BTC,
    ETH,
    BNB
}