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
            val daiRandomValue = (175..185).random()
            val daiRandomDecimal = (1..9).random()
            val btcRandomValue = (30000..60000).random()
            val btcRandomDecimal = (1..9).random()
            emit(
                listOf(
                    CurrencyValue(
                        daiRandomValue + 1f / daiRandomDecimal,
                        exchangeFrom = CurrencyType.ARS,
                        exchangeTo = CurrencyType.DAI,
                    ),
                    CurrencyValue(
                        btcRandomValue  + 1f / btcRandomDecimal,
                        exchangeFrom = CurrencyType.USD,
                        exchangeTo = CurrencyType.BTC,
                    )
                )
            )
            //Update currencies values each 5 minutes
//            delay(300000)
            delay(3000)
        }
    }
}