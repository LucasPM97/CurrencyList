package com.lucas.currencylist.ui.screens.favExchangeList.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.domain.useCases.PlatformState
import com.lucas.currencylist.ui.components.TradingWebList

@Composable
fun FavoriteList(
    platformsState: List<PlatformState>,
    itemFavOnClick: (currency: ExchangeValue) -> Unit
) {
    TradingWebList(
        platformsState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        itemFavOnClick = {
            itemFavOnClick(it)
        }
    )
}