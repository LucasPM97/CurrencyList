package com.lucas.currencylist.ui.screens.exchangeList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.lucas.currencylist.R


@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
    val noCurrenciesWeirdTitle = stringResource(id = R.string.currencies_emptyTitle)
    val noCurrenciesWeirdSubtitle =stringResource(id = R.string.currencies_emptySubtitle)
    val niceDayMessage = stringResource(id = R.string.currencies_emptyNiceDay)


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = noCurrenciesWeirdTitle,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        CenteredText(
            text = noCurrenciesWeirdSubtitle,
        )
        CenteredText(
            text = niceDayMessage,
        )
    }
}

@Composable
private fun CenteredText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center
    )
}