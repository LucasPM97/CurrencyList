package com.lucas.currencylist.models.repositories

import com.lucas.currencylist.models.CurrencyValue
import com.lucas.currencylist.models.services.BuenbitService
import com.lucas.currencylist.models.utils.extensions.toCurrencyList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICurrencyRepository{
    fun getCurrencyValues() : Flow<List<CurrencyValue>>
}

class CurrencyRepository(private val buenbitService: BuenbitService) : ICurrencyRepository {
    override fun getCurrencyValues(): Flow<List<CurrencyValue>> = flow {

        while(true){
            try {
                val response = buenbitService.getCurrencyExchangeValues()

                if (response.isSuccessful) {
                    response.body()?.let{
                        emit(it.buenbitObject.toCurrencyList())
                    }
                }

            } catch (ex: Exception) {
                println(ex)
            }

            //Update currencies values each 5 minutes
            delay(300000)
        }
    }
}