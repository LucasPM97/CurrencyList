package com.lucas.currencylist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.currencylist.R
import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.TradingPlatformType
import com.lucas.currencylist.models.TradingWeb
import com.lucas.currencylist.ui.screens.currencies.CurrencyList

@Composable
fun TradingWebCard(
    tradingWeb: TradingWeb,
    modifier: Modifier = Modifier
) {
    Card {
        Column(
            modifier
                .padding(all = 20.dp)
        ) {
            CurrencyList(
                currencies = tradingWeb.currecies,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "powered by",
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.buenbit),
                    contentDescription = "Buenbit image"
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewTradingWebCard() {
    TradingWebCard(
        tradingWeb = TradingWeb(
            currecies = listOf(
                CurrencyValue(
                    exchangeValue = 180.5f,
                    exchangeFrom = CurrencyType.ARS,
                    exchangeTo = CurrencyType.DAI
                ),
                CurrencyValue(
                    exchangeValue = 40000f,
                    exchangeFrom = CurrencyType.DAI,
                    exchangeTo = CurrencyType.BTC
                )
            ),
            platformType = TradingPlatformType.Buenbit
        )
    )
}