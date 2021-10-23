package com.lucas.core.utils.converters

import androidx.room.TypeConverter
import com.lucas.core.models.CurrencyType
import com.lucas.core.models.TradingPlatformType

class CurrencyTypeConverter {
    @TypeConverter
    fun toCurrencyType(value: Int) = enumValues<CurrencyType>()[value]

    @TypeConverter
    fun fromCurrencyType(value: CurrencyType) = value.ordinal
}