package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val noCurrenciesWeirdSubtitle =
    "It would be better if you go and watch some videos and learn how to create your own currency app..."
const val noCurrenciesWeirdTitle = "Mmmmm... no currencies?\nMmmmm...That's weird..."
const val niceDayMessage = "And... I don't know.. have a nice day"

@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
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