package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.getName

@Composable
fun CurrencyView(
    currencyValue: CurrencyValue,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = currencyValue.exchangeTitle,
            modifier = Modifier
                .padding(end = 20.dp),
        )
        CurrencyStack(
            exchangeFrom = currencyValue.exchangeFrom,
            exchangeTo = currencyValue.exchangeTo
        )
        Spacer(modifier = Modifier.fillMaxWidth())
        Text(
            text= currencyValue.exchangeValue.toString()
        )
    }
}

@Composable
private fun CurrencyStack(
    exchangeFrom: CurrencyType,
    exchangeTo: CurrencyType
) {
    Box(
        Modifier.width(45.dp)
    ) {
        CurrencyImage(
            exchangeFrom,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(30.dp)
        )
        CurrencyImage(
            exchangeTo,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(30.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCurrency() {
    CurrencyView(
        currencyValue = CurrencyValue(
            180.5f,
            exchangeFrom = CurrencyType.ARS,
            exchangeTo = CurrencyType.DAI,
        ),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}