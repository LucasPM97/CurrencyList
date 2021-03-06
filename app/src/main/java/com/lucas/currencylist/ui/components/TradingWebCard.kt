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
import com.lucas.core.models.*
import com.lucas.core.utils.extensions.getImage
import com.lucas.core.utils.extensions.getName
import com.lucas.core.utils.extensions.lastUpdateText
import com.lucas.core.utils.helpers.DateHelper
import com.lucas.currencylist.ui.screens.currencies.CurrencyList
import com.lucas.currencylist.ui.screens.currencies.components.ErrorMessage
import java.util.*
import com.lucas.currencylist.R

@Composable
fun TradingWebCard(
    tradingPlatformType: TradingPlatformType,
    tradingWebState: TradingWebProviderState,
    lastUpdate: Date?,
    currencyList: List<CurrencyValue>,
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
                    .replace("{0}",tradingPlatformType.getName())

                Image(
                    painter = painterResource(
                        id = tradingPlatformType.getImage()
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
    currencyList: List<CurrencyValue>,
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
    currencies: List<CurrencyValue>,
    lastUpdate: Date?,
    itemFavOnClick: (currencyId: String) -> Unit = {}
) {

    val context = LocalContext.current

    lastUpdate?.let {
        Row(
            modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = lastUpdate.lastUpdateText(context),
                fontSize = 12.sp,
                color = Color.Gray,
            )
        }
    }
    CurrencyList(
        modifier = Modifier.padding(bottom = 20.dp),
        currencies,
        itemFavOnClick
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_CompletedState() {
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.Completed(),
        currencyList = listOf(
            CurrencyValue(
                platform = TradingPlatformType.None,
                exchangeValue = 180.5,
                exchangeFrom = CurrencyType.ARS,
                exchangeTo = CurrencyType.DAI
            ),
            CurrencyValue(
                platform = TradingPlatformType.None,
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
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.Completed(),
        currencyList = emptyList(),
        lastUpdate = DateHelper.currentDate()
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Loading() {
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.IsLoading(),
        currencyList = emptyList(),
        lastUpdate = DateHelper.currentDate()
    )
}

@Composable
@Preview
fun PreviewTradingWebCard_Loading_WithItems() {
    TradingWebCard(
        tradingPlatformType = TradingPlatformType.Binance,
        tradingWebState = TradingWebProviderState.IsLoading(),
        currencyList = listOf(
            CurrencyValue(
                platform = TradingPlatformType.None,
                exchangeValue = 180.5,
                exchangeFrom = CurrencyType.ARS,
                exchangeTo = CurrencyType.DAI
            ),
            CurrencyValue(
                platform = TradingPlatformType.None,
                exchangeValue = 40000.0,
                exchangeFrom = CurrencyType.DAI,
                exchangeTo = CurrencyType.BTC
            )
        ),
        lastUpdate = DateHelper.currentDate()
    )
}