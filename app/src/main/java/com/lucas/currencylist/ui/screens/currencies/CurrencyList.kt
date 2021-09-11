package com.lucas.currencylist.ui.screens.currencies

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.ui.components.CurrencyView

@Composable
fun CurrencyList(
    currencyListLiveData: LiveData<List<CurrencyValue>>,
    modifier: Modifier = Modifier
) {
    val currencies by currencyListLiveData.observeAsState(initial = emptyList())

    LazyColumn(modifier = modifier) {
        itemsIndexed(currencies) { _, currencyValue ->
            CurrencyView(
                currencyValue,
                modifier = Modifier
                    .padding(bottom = 9.dp)
            )
        }
    }
}