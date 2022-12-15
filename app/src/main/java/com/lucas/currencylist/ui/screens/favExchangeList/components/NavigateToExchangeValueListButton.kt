package com.lucas.currencylist.ui.screens.favExchangeList.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.lucas.currencylist.NavigationConsts
import com.lucas.currencylist.R

@Composable
fun NavigateToExchangeValueListButton(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    fun goToExchangeValuesList() {
        navController?.navigate(NavigationConsts.EXCHANGE_LIST_SCREEN)
    }

    FloatingActionButton(
        modifier = modifier,
        onClick = {
            goToExchangeValuesList()
        },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.favorites_addCurrenciesButton_description)
        )
    }
}

@Composable
@Preview
fun PreviewButton() {
    NavigateToExchangeValueListButton()
}