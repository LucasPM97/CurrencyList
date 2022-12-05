package com.lucas.core.utils.converters

import androidx.room.TypeConverter
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.TradingPlatformType
import java.util.*

class DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}