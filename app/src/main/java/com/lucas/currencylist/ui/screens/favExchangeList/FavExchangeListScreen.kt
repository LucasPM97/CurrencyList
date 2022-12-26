package com.lucas.currencylist.ui.screens.favExchangeList

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.lucas.core.data.models.ExchangeValue
import com.lucas.currencylist.ui.screens.favExchangeList.components.FavExchangeList
import com.lucas.currencylist.ui.screens.favExchangeList.components.NavigateToExchangeValueListButton
import com.lucas.currencylist.ui.screens.favExchangeList.components.TopBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun FavExchangeListScreen(
    navController: NavController? = null,
    viewModel: FavExchangeListViewModel = getViewModel()
) {
    val platformsState by viewModel.currencies.collectAsState(null)

    fun favCurrency(currency: ExchangeValue) {
        viewModel.updateFav(currency.currencyId, !currency.fav)
    }

    ScreenScaffold(
        navController = navController
    ) { it ->
        Column(modifier = Modifier.padding(it)) {
            FavExchangeList(
                platformsState,
                onFavExchangeValue = { exchangeValueFav ->
                    favCurrency(exchangeValueFav)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    navController: NavController?,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            NavigateToExchangeValueListButton(
                navController = navController
            )
        },
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    FavExchangeListScreen()
}