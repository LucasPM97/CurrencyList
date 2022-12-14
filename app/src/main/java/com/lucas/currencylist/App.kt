package com.lucas.currencylist

import android.app.Application
import com.lucas.core.di.coreModule
import com.lucas.currencylist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@App)
            // Implement Koin WorkManager
            workManagerFactory()
            // Load modules
            modules(
                appModule,
                coreModule
            )
        }
    }
}