package com.lucas.core.data.local.database.exchangeValues

import androidx.room.*
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ExchangeValuesDatabaseDAO {

    @Query("Select * FROM exchange_table")
    fun getAllExchangeValuesFlow(): Flow<List<ExchangeValue>>

    @Query("Select * FROM exchange_table WHERE fav == 1")
    fun getAllFavExchangeValuesFlow(): Flow<List<ExchangeValue>>

    @Query("Select * FROM exchange_table WHERE platform == :platformType")
    fun getCurrenciesFlowFromPlatform(platformType: ExchangePlatformType): Flow<List<ExchangeValue>>

    @Query("Select * FROM exchange_table WHERE platform == :platformType AND fav == 1")
    fun getFavCurrenciesFlowFromPlatform(platformType: ExchangePlatformType): Flow<List<ExchangeValue>>

    @Query("Select * FROM exchange_table WHERE currencyId == :currencyId")
    suspend fun getCurrencyById(currencyId: String): ExchangeValue?

    @Insert
    suspend fun insertCurrency(currency: ExchangeValue)

    @Query("UPDATE exchange_table SET exchangeValue=:exchangeValue  WHERE currencyId = :currencyId")
    suspend fun updateExchangeValues(currencyId: String, exchangeValue: Double)

    @Query("UPDATE exchange_table SET fav=:fav WHERE currencyId = :currencyId")
    suspend fun updateFav(currencyId: String, fav: Boolean)

    @Query("DELETE FROM exchange_table")
    suspend fun clear()
}