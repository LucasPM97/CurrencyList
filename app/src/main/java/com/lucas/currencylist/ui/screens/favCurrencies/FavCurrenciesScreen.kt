package com.lucas.currencylist.ui.screens.favCurrencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FavCurrenciesScreen(navController: NavController?) {

    fun goToCurrencyList(){
        navController?.let {
            it.navigate("currencies")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Here will be listed your selected currencies")
        Button(
            modifier = Modifier
                .padding(top = 20.dp),
            onClick = {
                goToCurrencyList()
            }
        ){
            Text("All currencies")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    FavCurrenciesScreen(navController = null)
}