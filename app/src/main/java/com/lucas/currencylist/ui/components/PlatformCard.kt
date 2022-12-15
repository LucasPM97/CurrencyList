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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucas.core.data.models.CurrencyType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.TradingWebProviderState
import com.lucas.core.domain.extensions.getName
import com.lucas.core.domain.helpers.DateHelper
import com.lucas.currencylist.ui.screens.exchangeList.components.ErrorMessage
import java.util.*
import com.lucas.currencylist.R
import com.lucas.currencylist.domain.extensions.getImage
import com.lucas.currencylist.domain.extensions.lastUpdateText

@Composable
fun PlatformCard(
    exchangePlatformType: ExchangePlatformType,
    tradingWebState: TradingWebProviderState = TradingWebProviderState.Completed(),
    lastUpdate: Date?,
    currencyList: List<ExchangeValue>,
    itemFavOnClick: (currencyId: String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Card(modifier) {
        Column(
            Modifier
                .padding(all = 10.dp)
                .animateContentSize()
        ) {

            RenderBody(
                tradingWebState,
                lastUpdate,
                currencyList,
                itemFavOnClick
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.webcard_poweredBy),
                    modifier = Modifier
                        .padding(end = 10.dp),
                    fontWeight = FontWeight.Bold
                )

                val webImageDescription = stringResource(id = R.string.webcard_image_description)
                    .replace("{0}", exchangePlatformType.getName())

                Image(
                    painter = painterResource(
                        id = exchangePlatformType.getImage()
                    ),
                    contentDescription = webImageDescription
                )
            }
        }
    }
}

@Composable
private fun RenderBody(
    tradingWebState: TradingWebProviderState,
    lastUpdate: Date?,
    currencyList: List<ExchangeValue>,
    itemFavOnClick: (currencyId: String) -> Unit = {}
) {
    if (tradingWebState is TradingWebProviderState.Completed) {
        if (currencyList.isEmpty()) {
            RenderErrorMessage()
        } else {
            RenderList(
                currencies = currencyList,
                lastUpdate,
                itemFavOnClick
            )
        }
    } else if (tradingWebState is TradingWebProviderState.IsLoading) {
        if (currencyList.isEmpty()) {
            RenderLoading()
        } else {
            RenderList(
                currencies = currencyList,
                lastUpdate,
                itemFavOnClick
            )
        }
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
private fun RenderList(
    currencies: List<ExchangeValue>,
    lastUpdate: Date?,
    itemFavOnClick: (currencyId: String) -> Unit = {}
) {

    val context = LocalContext.current

    lastUpdate?.let {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = lastUpdate.lastUpdateText(context),
                fontSize = 12.sp,
                color = Color.Gray,
            )
        }
    }
    ExchangeValueList(
        modifier = Modifier.padding(bottom = 20.dp),
        currencies,
        itemFavOnClick
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_CompletedState() {
    PlatformCard(
        exchangePlatformType = ExchangePlatformType.Binance,
        tradingWebState = TradingWebProviderState.Completed(),
        currencyList = listOf(
            ExchangeValue(
                platform = ExchangePlatformType.None,
                exchangeValue = 180.5,
                exchangeFrom = CurrencyType.ARS,
                exchangeTo = CurrencyType.DAI
            ),
            ExchangeValue(
                platform = ExchangePlatformType.None,
                exchangeValue = 40000.0,
                exchangeFrom = CurrencyType.DAI,
                exchangeTo = CurrencyType.BTC
            )
        ),
        lastUpdate = DateHelper.currentDate()
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Error() {
    PlatformCard(
        exchangePlatformType = ExchangePlatformType.Binance,
        tradingWebState = TradingWebProviderState.Completed(),
        currencyList = emptyList(),
        lastUpdate = DateHelper.currentDate()
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Loading() {
    PlatformCard(
        exchangePlatformType = ExchangePlatformType.Binance,
        tradingWebState = TradingWebProviderState.IsLoading(),
        currencyList = emptyList(),
        lastUpdate = DateHelper.currentDate()
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Loading_WithItems() {
    PlatformCard(
        exchangePlatformType = ExchangePlatformType.Binance,
        tradingWebState = TradingWebProviderState.IsLoading(),
        currencyList = listOf(
            ExchangeValue(
                platform = ExchangePlatformType.None,
                exchangeValue = 180.5,
                exchangeFrom = CurrencyType.ARS,
                exchangeTo = CurrencyType.DAI
            ),
            ExchangeValue(
                platform = ExchangePlatformType.None,
                exchangeValue = 40000.0,
                exchangeFrom = CurrencyType.DAI,
                exchangeTo = CurrencyType.BTC
            )
        ),
        lastUpdate = DateHelper.currentDate()
    )
}