package com.lucas.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.local.database.converters.DateTimeConverter


@Database(entities = [ExchangeValue::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class ExchangeValueDatabase : RoomDatabase() {

    abstract val dao: ExchangeValuesDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: ExchangeValueDatabase? = null

        fun getInstance(context: Context): ExchangeValueDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExchangeValueDatabase::class.java,
                        "currencies_database"
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