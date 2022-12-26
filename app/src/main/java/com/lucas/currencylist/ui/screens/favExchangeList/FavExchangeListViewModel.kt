package com.lucas.currencylist.ui.screens.favExchangeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.lucas.core.domain.useCases.IGetFavExchangeValuesUseCase
import com.lucas.core.domain.useCases.IUpdateExchangeValueFavUseCase
import kotlinx.coroutines.launch

class FavExchangeListViewModel(
    private val getFavExchangeValues: IGetFavExchangeValuesUseCase,
    private val updateExchangeValueFav: IUpdateExchangeValueFavUseCase
) : ViewModel() {

    val currencies = getFavExchangeValues()

    fun updateFav(currencyId: String, fav: Boolean) = viewModelScope.launch {
        updateExchangeValueFav(currencyId, fav)
    }
}