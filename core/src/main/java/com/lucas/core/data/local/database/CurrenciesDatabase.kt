package com.lucas.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.utils.converters.DateTimeConverter


@Database(entities = [CurrencyValue::class], version = 5, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class CurrenciesDatabase : RoomDatabase() {

    abstract val dao: CurrenciesDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: CurrenciesDatabase? = null

        fun getInstance(context: Context): CurrenciesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CurrenciesDatabase::class.java,
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