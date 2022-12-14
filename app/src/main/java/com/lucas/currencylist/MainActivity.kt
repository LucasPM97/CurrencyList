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
import com.lucas.currencylist.ui.screens.exchangeList.ExchangeListScreen
import com.lucas.currencylist.ui.screens.favExchangeList.FavExchangeListScreen
import com.lucas.currencylist.ui.theme.CurrencyListTheme

class MainActivity : ComponentActivity() {
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
    }
}

@Composable
fun AppComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "favCurrencies") {
        composable("favCurrencies") { FavExchangeListScreen(navController) }
        composable("currencies") { ExchangeListScreen(navController) }
    }
}