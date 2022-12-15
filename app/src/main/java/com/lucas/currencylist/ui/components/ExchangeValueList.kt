package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.ExchangeValue

@Composable
fun ExchangeValueList(
    modifier: Modifier = Modifier,
    exchangeValues: List<ExchangeValue>,
    itemFavOnClick: (exchangeValueId: String) -> Unit = {}

) {
    Column(modifier) {
        exchangeValues.forEach {
            ExchangeValueView(
                it,
                modifier = Modifier
                    .padding(bottom = 9.dp),
                favOnClick = itemFavOnClick
            )
        }
    }
}

