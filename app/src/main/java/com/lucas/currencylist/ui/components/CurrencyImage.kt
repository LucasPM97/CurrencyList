package com.lucas.currencylist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.getImage
import com.lucas.currencylist.models.getImageName

@Composable
fun CurrencyImage(
    currencyType: CurrencyType,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(
            currencyType.getImage()
        ),
        contentDescription = currencyType.getImageName(),
        modifier = modifier
    )
}