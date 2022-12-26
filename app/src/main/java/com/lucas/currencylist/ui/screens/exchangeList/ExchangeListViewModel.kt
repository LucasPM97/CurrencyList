package com.lucas.currencylist.ui.screens.exchangeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.core.domain.useCases.IGetExchangeValuesUseCase
import com.lucas.core.domain.useCases.IUpdateExchangeValueFavUseCase
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getExchangeValues: IGetExchangeValuesUseCase,
    private val updateExchangeValueFav: IUpdateExchangeValueFavUseCase
) : ViewModel() {

    val currencies = getExchangeValues()

    fun updateFav(currencyId: String, fav: Boolean) = viewModelScope.launch {
        updateExchangeValueFav(currencyId, fav)
    }
}