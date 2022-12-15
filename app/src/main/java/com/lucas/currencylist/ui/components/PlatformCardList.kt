package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.domain.useCases.PlatformState

val itemsSpace = 10.dp

@Composable
fun PlatformCardList(
    platformState: List<PlatformState>,
    modifier: Modifier = Modifier,
    itemFavOnClick: (currency: ExchangeValue) -> Unit
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        platformState.forEach { platformState ->
            RenderTradingWeb(
                platformState,
                itemFavOnClick = { exchangeValueId ->
                    val currency = platformState.exchangeValues.first {
                        it.currencyId == exchangeValueId
                    }
                    itemFavOnClick(currency)
                }
            )
        }
    }
}


@Composable
private fun RenderTradingWeb(
    platformState: PlatformState,
    modifier: Modifier = Modifier,
    itemFavOnClick: (currencyId: String) -> Unit
) {
    PlatformCard(
        platformState.platformType,
        lastUpdate = platformState.lastUpdate,
        currencyList = platformState.exchangeValues,
        itemFavOnClick = itemFavOnClick,
        modifier = modifier.padding(
            top = itemsSpace,
            bottom = itemsSpace
        )
    )
}