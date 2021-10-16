package com.lucas.currencylist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lucas.core.models.CurrencyType
import com.lucas.core.utils.extensions.getImage
import com.lucas.core.utils.extensions.getImageName

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