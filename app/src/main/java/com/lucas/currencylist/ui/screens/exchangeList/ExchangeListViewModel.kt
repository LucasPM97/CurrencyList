package com.lucas.currencylist.ui.screens.exchangeList

import androidx.lifecycle.ViewModel
import com.lucas.core.domain.useCases.IGetExchangeValuesUseCase
import com.lucas.core.domain.useCases.IUpdateExchangeValueFavUseCase

class ExchangeListViewModel(
    private val getExchangeValues: IGetExchangeValuesUseCase,
    private val updateExchangeValueFav: IUpdateExchangeValueFavUseCase
) : ViewModel() {

    val currencies = getExchangeValues()

    suspend fun updateFav(currencyId: String, fav: Boolean) =
        updateExchangeValueFav(currencyId, fav)
}