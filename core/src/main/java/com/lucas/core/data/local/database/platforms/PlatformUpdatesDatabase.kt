package com.lucas.core.data.local.database.platforms

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucas.core.data.local.database.converters.DateTimeConverter
import com.lucas.core.data.models.TradingPlatformUpdates

@Database(entities = [TradingPlatformUpdates::class], version = 4, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class PlatformUpdatesDatabase : RoomDatabase() {

    abstract val dao: PlatformUpdatesDatabaseDAO
}