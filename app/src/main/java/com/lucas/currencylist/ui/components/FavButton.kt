package com.lucas.currencylist.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucas.currencylist.R

@Composable
fun FavButton(fav: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick,
        Modifier.padding(start = 5.dp)
    ) {
        Icon(
            imageVector = if (fav) Icons.Filled.Clear
            else Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.favButton_description)
        )
    }
}