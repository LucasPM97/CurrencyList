package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucas.core.models.CurrencyType
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.core.utils.extensions.lastUpdateText

@Composable
fun CurrencyView(
    currencyValue: CurrencyValue,
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
                text = currencyValue.exchangeTitle,
                modifier = Modifier
                    .padding(end = 20.dp),
            )
            CurrencyStack(
                exchangeFrom = currencyValue.exchangeFrom,
                exchangeTo = currencyValue.exchangeTo
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = currencyValue.exchangeString,
                    textAlign = TextAlign.End
                )
                FavButton(
                    currencyValue.fav,
                    onClick = {
                        favOnClick(currencyValue.currencyId)
                    }
                )
            }
        }
//        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = currencyValue.lastUpdateText(),
            fontSize = 12.sp,
            color = Color.Gray
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
            platform = TradingPlatformType.None,
            180.5,
            exchangeFrom = CurrencyType.ARS,
            exchangeTo = CurrencyType.DAI,
        ),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}