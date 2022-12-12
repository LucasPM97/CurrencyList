package com.lucas.currencylist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lucas.core.data.models.CurrencyType
import com.lucas.currencylist.domain.extensions.getImage
import com.lucas.currencylist.domain.extensions.getImageName

@Composable
fun CurrencyImage(
    currencyType: CurrencyType,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(
            currencyType.getImage()
        ),
        contentDescription = stringResource(id =  currencyType.getImageName()),
        modifier = modifier
    )
}