package com.lucas.core.utils.converters

import androidx.room.TypeConverter
import com.lucas.core.models.TradingPlatformType

class TradingPlatformConverter {
    @TypeConverter
    fun toPlatformType(value: Int) = enumValues<TradingPlatformType>()[value]

    @TypeConverter
    fun fromPlatformType(value: TradingPlatformType) = value.ordinal
}