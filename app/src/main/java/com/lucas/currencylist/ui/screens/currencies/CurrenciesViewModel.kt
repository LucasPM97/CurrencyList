package com.lucas.currencylist.ui.screens.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lucas.currencylist.models.repositories.CurrencyRepository
import com.lucas.currencylist.models.repositories.ICurrencyRepository
import com.lucas.currencylist.models.services.RetrofitBuilder

class CurrenciesViewModel : ViewModel() {

    private val repository: ICurrencyRepository = CurrencyRepository(
        RetrofitBuilder.buenbitService
    )

    val currencies = repository.getCurrencyValues().asLiveData()
}