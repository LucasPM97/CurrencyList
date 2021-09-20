package com.lucas.currencylist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.utils.extensions.getImage
import com.lucas.currencylist.models.utils.extensions.getImageName

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