package com.lucas.currencylist.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.core.models.*
import com.lucas.core.models.utils.extensions.getImage
import com.lucas.core.models.utils.extensions.getImageName
import com.lucas.currencylist.ui.screens.currencies.CurrencyList
import com.lucas.currencylist.ui.screens.currencies.ErrorMessage

@Composable
fun TradingWebCard(
    tradingPlatformType: TradingPlatformType,
    tradingWebState: TradingWebProviderState,
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Column(
            Modifier
                .padding(all = 20.dp)
                .animateContentSize()
        ) {

            RenderBody(tradingWebState)

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "powered by",
                    modifier = Modifier
                        .padding(end = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(
                        id = tradingPlatformType.getImage()
                    ),
                    contentDescription = "${tradingPlatformType.getImageName()} image"
                )
            }
        }
    }
}

@Composable
private fun RenderBody(tradingWebState: TradingWebProviderState) {
    if (tradingWebState is TradingWebProviderState.Completed) {
        if (tradingWebState.tradingWeb.currecies.isEmpty()) {
            RenderErrorMessage()
        } else {
            RenderList(currencies = tradingWebState.tradingWeb.currecies)
        }
    } else if (tradingWebState is TradingWebProviderState.IsLoading) {
        RenderLoading()
    }
}

@Composable
fun RenderErrorMessage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorMessage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )
    }
}

@Composable
fun RenderLoading() {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp),

            )
    }
}

@Composable
private fun RenderList(currencies: List<CurrencyValue>) {
    CurrencyList(
        currencies = currencies,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_CompletedState() {
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.Completed(
            TradingWeb(
                currecies = listOf(
                    CurrencyValue(
                        exchangeValue = 180.5,
                        exchangeFrom = CurrencyType.ARS,
                        exchangeTo = CurrencyType.DAI
                    ),
                    CurrencyValue(
                        exchangeValue = 40000.0,
                        exchangeFrom = CurrencyType.DAI,
                        exchangeTo = CurrencyType.BTC
                    )
                ),
                platformType = TradingPlatformType.Binance
            )
        )
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Error() {
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.Completed(
            TradingWeb(
                currecies = emptyList(),
                platformType = TradingPlatformType.Binance
            )
        )
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Loading() {
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.IsLoading()
    )
}