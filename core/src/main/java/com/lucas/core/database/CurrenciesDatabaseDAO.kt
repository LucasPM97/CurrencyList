package com.lucas.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType

@Dao
interface CurrenciesDatabaseDAO {

    @Query("Select * FROM currencies_table WHERE platform == :platformType")
    suspend fun getAllCurrenciesFromPlatform(platformType: TradingPlatformType): List<CurrencyValue>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateList(currencies: List<CurrencyValue>)

    @Query("DELETE FROM currencies_table")
    suspend fun clear()
}