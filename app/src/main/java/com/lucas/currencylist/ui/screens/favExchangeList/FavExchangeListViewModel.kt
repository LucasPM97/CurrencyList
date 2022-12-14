package com.lucas.currencylist.ui.screens.favExchangeList

import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import com.lucas.core.domain.useCases.IGetFavExchangeValuesUseCase
import com.lucas.core.domain.useCases.IUpdateExchangeValueFavUseCase

class FavExchangeListViewModel(
    private val getFavExchangeValues: IGetFavExchangeValuesUseCase,
    private val updateExchangeValueFav: IUpdateExchangeValueFavUseCase
) : ViewModel() {

    val currencies = getFavExchangeValues()

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        updateExchangeValueFav(currencyId, fav)
    }
}