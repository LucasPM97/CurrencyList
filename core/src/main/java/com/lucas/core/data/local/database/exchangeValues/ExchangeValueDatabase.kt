package com.lucas.core.data.local.database.exchangeValues

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucas.core.data.local.database.converters.DateTimeConverter
import com.lucas.core.data.models.ExchangeValue

@Database(entities = [ExchangeValue::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class ExchangeValueDatabase : RoomDatabase() {

    abstract val dao: ExchangeValuesDatabaseDAO
}