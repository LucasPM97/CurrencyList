package com.lucas.currencylist.ui.screens.exchangeList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.domain.useCases.PlatformState
import com.lucas.currencylist.ui.components.PlatformCardList
import com.lucas.currencylist.ui.screens.exchangeList.components.TopBar
import org.koin.androidx.compose.getViewModel

@Composable
fun ExchangeListScreen(
    navController: NavController?,
    viewModel: ExchangeListViewModel = getViewModel()
) {
    fun favExchangeValue(exchangeValue: ExchangeValue) {
        viewModel.updateFav(exchangeValue.currencyId, !exchangeValue.fav)
    }

    val platformsState by viewModel.currencies.collectAsState(initial = null)

    ScreenScaffold(navController = navController) {
        Column(modifier = Modifier.padding(it)) {
            ScreenContent(
                platformsState,
                onFavExchangeValue = { exchangeValue ->
                    favExchangeValue(exchangeValue)
                }
            )
        }
    }
}

@Composable
private fun ScreenContent(
    platformsState: List<PlatformState>?,
    onFavExchangeValue: (exchangeValue: ExchangeValue) -> Unit
) {

    platformsState?.let { it ->
        PlatformCardList(
            platformsState = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            itemFavOnClick = onFavExchangeValue
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    navController: NavController?,
    content: @Composable (PaddingValues) -> Unit
) {

    fun onBack() {
        navController?.popBackStack()
    }

    Scaffold(
        topBar = {
            TopBar(onBack = { onBack() })
        },
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    ExchangeListScreen(navController = null)
}