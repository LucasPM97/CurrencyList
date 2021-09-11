package com.lucas.currencylist.models.repositories

import com.lucas.currencylist.models.CurrencyType
import com.lucas.currencylist.models.CurrencyValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICurrencyRepository{
    fun getCurrencyValues() : Flow<List<CurrencyValue>>
}

class CurrencyRepository : ICurrencyRepository {
    override fun getCurrencyValues(): Flow<List<CurrencyValue>> = flow {
        while(true){
            emit(
                listOf(
                    CurrencyValue(
                        180.5f,
                        exchangeFrom = CurrencyType.ARS,
                        exchangeTo = CurrencyType.DAI,
                    ),
                    CurrencyValue(
                        40000f,
                        exchangeFrom = CurrencyType.USD,
                        exchangeTo = CurrencyType.BTC,
                    )
                )
            )
            //Update currencies values each 5 minutes
            delay(300000)
        }
    }
}