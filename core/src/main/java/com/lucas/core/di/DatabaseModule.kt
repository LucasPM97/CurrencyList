package com.lucas.core.di

import androidx.room.Room
import com.lucas.core.data.local.database.exchangeValues.ExchangeValueDatabase
import com.lucas.core.data.local.database.platforms.PlatformUpdatesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ExchangeValueDatabase::class.java,
            "currencies_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            PlatformUpdatesDatabase::class.java,
            "platform_updates_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}