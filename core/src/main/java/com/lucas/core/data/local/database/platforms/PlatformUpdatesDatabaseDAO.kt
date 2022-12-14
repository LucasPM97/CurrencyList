package com.lucas.core.data.local.database.platforms

import androidx.room.*
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.TradingPlatformUpdates
import kotlinx.coroutines.flow.Flow
import java.util.*
import kotlin.collections.List

@Dao
interface PlatformUpdatesDatabaseDAO {

    @Query("Select * FROM platform_updates ORDER BY platformType")
    fun getAllPlatformsLastUpdateFlow(): Flow<List<TradingPlatformUpdates>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlatformUpdates(
        platformUpdates: TradingPlatformUpdates,
    )

    @Query("DELETE FROM platform_updates")
    suspend fun clear()
}