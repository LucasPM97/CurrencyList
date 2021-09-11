package com.lucas.currencylist.ui.screens.currencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.repositories.CurrencyRepository
import com.lucas.currencylist.models.repositories.ICurrencyRepository

class CurrenciesViewModel : ViewModel() {

    private val repository: ICurrencyRepository = CurrencyRepository()

    private val _currencies = MutableLiveData<List<CurrencyValue>>()
    val currencies = repository.getCurrencyValues().asLiveData()
}