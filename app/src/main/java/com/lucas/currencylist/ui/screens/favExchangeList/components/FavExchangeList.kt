package com.lucas.currencylist.ui.screens.favExchangeList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.domain.useCases.PlatformState
import com.lucas.currencylist.R
import com.lucas.currencylist.ui.components.PlatformCardList

@Composable
fun FavExchangeList(
    platformsState: List<PlatformState>?,
    onFavExchangeValue: (ExchangeValue) -> Unit,
    modifier: Modifier = Modifier
) {
    platformsState?.let {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val anyExchangeValuesStored = it.any {
                it.exchangeValues.any()
            }

            if (!anyExchangeValuesStored) {
                EmptyFavListMessage()
            } else {
                PlatformCardList(
                    platformsState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    itemFavOnClick = onFavExchangeValue
                )
            }
        }
    }
}


@Composable
private fun EmptyFavListMessage() {
    Text(text = stringResource(R.string.favScreen_emptyList_message))
}