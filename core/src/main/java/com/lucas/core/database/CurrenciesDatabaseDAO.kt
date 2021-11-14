package com.lucas.core.database

import androidx.room.*
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDatabaseDAO {

    @Query("Select * FROM currencies_table WHERE platform == :platformType")
    fun getCurrenciesFlowFromPlatform(platformType: TradingPlatformType): Flow<List<CurrencyValue>>

    @Query("Select * FROM currencies_table WHERE platform == :platformType AND fav == 1")
    fun getFavCurrenciesFlowFromPlatform(platformType: TradingPlatformType): Flow<List<CurrencyValue>>

    @Query("Select * FROM currencies_table WHERE currencyId == :currencyId")
    fun getCurrencyById(currencyId: String): CurrencyValue?

    @Insert
    suspend fun insertCurrency(currency: CurrencyValue)

    @Query("UPDATE currencies_table SET exchangeValue=:exchangeValue WHERE currencyId = :currencyId")
    suspend fun updateExchangeValues(currencyId: String, exchangeValue: Double)

    @Query("UPDATE currencies_table SET fav=:fav WHERE currencyId = :currencyId")
    suspend fun updateFav(currencyId: String, fav: Boolean)

    @Query("DELETE FROM currencies_table")
    suspend fun clear()
}