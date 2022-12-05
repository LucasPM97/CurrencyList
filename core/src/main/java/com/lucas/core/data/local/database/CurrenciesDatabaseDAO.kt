package com.lucas.core.data.local.database

import androidx.room.*
import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.ExchangePlatformType
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CurrenciesDatabaseDAO {

    @Query("Select * FROM currencies_table WHERE platform == :platformType")
    fun getCurrenciesFlowFromPlatform(platformType: ExchangePlatformType): Flow<List<CurrencyValue>>

    @Query("Select * FROM currencies_table WHERE platform == :platformType AND fav == 1")
    fun getFavCurrenciesFlowFromPlatform(platformType: ExchangePlatformType): Flow<List<CurrencyValue>>

    @Query("Select * FROM currencies_table WHERE currencyId == :currencyId")
    suspend fun getCurrencyById(currencyId: String): CurrencyValue?

    @Insert
    suspend fun insertCurrency(currency: CurrencyValue)

    @Query("UPDATE currencies_table SET exchangeValue=:exchangeValue  WHERE currencyId = :currencyId")
    suspend fun updateExchangeValues(currencyId: String, exchangeValue: Double)

    @Query("UPDATE currencies_table SET fav=:fav WHERE currencyId = :currencyId")
    suspend fun updateFav(currencyId: String, fav: Boolean)

    @Query("DELETE FROM currencies_table")
    suspend fun clear()
}