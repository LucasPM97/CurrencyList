package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.CurrencyValue
import com.lucas.currencylist.ui.components.CurrencyView

@Composable
fun CurrencyList(
    modifier: Modifier = Modifier,
    currencies: List<CurrencyValue>,
    itemFavOnClick: (currencyId: String) -> Unit = {}

) {
    Column(modifier) {
        currencies.forEach {
            CurrencyView(
                it,
                modifier = Modifier
                    .padding(bottom = 9.dp),
                favOnClick = itemFavOnClick
            )
        }
    }
}

