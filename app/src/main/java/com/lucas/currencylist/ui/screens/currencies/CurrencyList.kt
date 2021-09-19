package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.ui.components.CurrencyView

@Composable
fun CurrencyList(
    currencies: List<CurrencyValue>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        currencies.forEach {
            CurrencyView(
                it,
                modifier = Modifier
                    .padding(bottom = 9.dp)
            )
        }
    }
}

