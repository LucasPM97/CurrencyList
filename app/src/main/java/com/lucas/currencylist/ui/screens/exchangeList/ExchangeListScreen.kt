package com.lucas.currencylist.ui.screens.exchangeList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.core.data.models.ExchangeValue
import com.lucas.currencylist.ui.components.PlatformCardList
import com.lucas.currencylist.ui.screens.exchangeList.components.TopBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ExchangeListScreen(
    navController: NavController?,
    viewModel: ExchangeListViewModel = getViewModel()
) {
    fun onBack() {
        navController?.popBackStack()
    }

    Scaffold(
        topBar = {
            TopBar(onBack = { onBack() })
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Screen(viewModel)
            }
        }
    )
}

@Composable
private fun Screen(viewModel: ExchangeListViewModel) {
    val coroutineScope = rememberCoroutineScope()

    fun favExchangeValue(exchangeValue: ExchangeValue) {
        coroutineScope.launch {
            viewModel.updateFav(exchangeValue.currencyId, !exchangeValue.fav)
        }
    }

    val platformsState by viewModel.currencies.collectAsState(initial = null)

    platformsState?.let { it ->
        PlatformCardList(
            platformState = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            itemFavOnClick = { exchangeValue ->
                favExchangeValue(exchangeValue)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    ExchangeListScreen(navController = null)
}