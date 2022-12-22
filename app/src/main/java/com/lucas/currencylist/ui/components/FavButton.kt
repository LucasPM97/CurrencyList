package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucas.currencylist.R
import com.lucas.currencylist.ui.utils.TestTagConsts

@Composable
fun FavButton(fav: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick,
        Modifier
            .padding(start = 5.dp)
            .testTag(TestTagConsts.Components.FavButton.FAV_BUTTON_TAG)
    ) {
        Icon(
            imageVector = if (fav) Icons.Filled.Clear
            else Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.favButton_description)
        )
    }
}