package com.lucas.core.database

import androidx.room.*
import com.lucas.core.models.CurrencyValue
import com.lucas.core.models.TradingPlatformType
import com.lucas.core.models.TradingPlatformUpdates
import com.lucas.core.utils.helpers.DateHelper
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface PlatformUpdatesDatabaseDAO {

    @Query("Select lastUpdate FROM platform_updates WHERE platformName == :platformName")
    fun getFlowPlatformLastUpdate(platformName: String): Flow<Date>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlatformUpdates(
        platformUpdates: TradingPlatformUpdates,
    )

    @Query("DELETE FROM platform_updates")
    suspend fun clear()
}