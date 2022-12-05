package com.lucas.core.data.local.database

import androidx.room.*
import com.lucas.core.data.models.TradingPlatformUpdates
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