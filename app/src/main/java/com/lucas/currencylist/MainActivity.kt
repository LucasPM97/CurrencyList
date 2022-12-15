package com.lucas.currencylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.*
import com.lucas.core.data.workers.ExchangeFetchWorker
import com.lucas.currencylist.ui.screens.exchangeList.ExchangeListScreen
import com.lucas.currencylist.ui.screens.favExchangeList.FavExchangeListScreen
import com.lucas.currencylist.ui.theme.CurrencyListTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppComponent()
                }
            }
        }

        runWorkers()
    }

    private fun runWorkers() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val request = PeriodicWorkRequestBuilder<ExchangeFetchWorker>(30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .addTag(ExchangeFetchWorker.TAG)
            .build()
        workManager.enqueueUniquePeriodicWork(
            ExchangeFetchWorker.TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    @Composable
    private fun AppComponent() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavigationConsts.FAV_EXCHANGE_LIST_SCREEN
        ) {
            composable(NavigationConsts.FAV_EXCHANGE_LIST_SCREEN) {
                FavExchangeListScreen(
                    navController
                )
            }
            composable(NavigationConsts.EXCHANGE_LIST_SCREEN) { ExchangeListScreen(navController) }
        }
    }
}

object NavigationConsts {
    const val FAV_EXCHANGE_LIST_SCREEN = "favExchangeValuesList"
    const val EXCHANGE_LIST_SCREEN = "exchangeValuesList"

}