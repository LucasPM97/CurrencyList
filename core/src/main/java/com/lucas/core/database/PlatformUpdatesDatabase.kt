package com.lucas.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucas.core.models.TradingPlatformUpdates
import com.lucas.core.utils.converters.DateTimeConverter


@Database(entities = [TradingPlatformUpdates::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class PlatformUpdatesDatabase : RoomDatabase() {

    abstract val dao: PlatformUpdatesDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: PlatformUpdatesDatabase? = null

        fun getInstance(context: Context): PlatformUpdatesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlatformUpdatesDatabase::class.java,
                        "platform_updates_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}