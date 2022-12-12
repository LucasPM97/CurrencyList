package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.ExchangePlatformType

@Composable
fun CurrencyView(
    ExchangeValue: ExchangeValue,
    modifier: Modifier = Modifier,
    favOnClick: (currencyId: String) -> Unit = {}
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = ExchangeValue.exchangeTitle,
                modifier = Modifier
                    .padding(end = 20.dp),
            )
            CurrencyStack(
                exchangeFrom = ExchangeValue.exchangeFrom,
                exchangeTo = ExchangeValue.exchangeTo
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = ExchangeValue.exchangeString,
                    textAlign = TextAlign.End
                )
                FavButton(
                    ExchangeValue.fav,
                    onClick = {
                        favOnClick(ExchangeValue.currencyId)
                    }
                )
            }
        }
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
        ExchangeValue = ExchangeValue(
            platform = ExchangePlatformType.None,
            180.5,
            exchangeFrom = CurrencyType.ARS,
            exchangeTo = CurrencyType.DAI,
        ),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}