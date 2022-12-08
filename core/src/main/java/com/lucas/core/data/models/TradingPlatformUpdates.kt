package com.lucas.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucas.core.utils.helpers.DateHelper
import java.util.*

@Entity(tableName = "platform_updates")
data class TradingPlatformUpdates(
    @PrimaryKey
    var platformType: ExchangePlatformType,
    var lastUpdate: Date = DateHelper.currentDate()
)