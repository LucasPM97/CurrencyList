package com.lucas.core.mock.data.repositories

import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.TradingPlatformUpdates
import com.lucas.core.data.repositories.IExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeExchangeRepository : IExchangeRepository {

    private val exchangeValues = mutableListOf<ExchangeValue>()
    private val platformUpdates = mutableListOf<TradingPlatformUpdates>()

    override suspend fun fetchExchangeValues(): Boolean {
        return true
    }

    override fun getExchangeValues(): Flow<List<ExchangeValue>> = flow {
        emit(exchangeValues)
    }

    override fun getFavExchangeValues(): Flow<List<ExchangeValue>> = flow {
        emit(exchangeValues.filter {
            it.fav
        })
    }

    override fun getPlatformsLastUpdateFlow(): Flow<List<TradingPlatformUpdates>> = flow {
        emit(platformUpdates)
    }

    override suspend fun updateExchangeValueFav(currencyId: String, fav: Boolean) {
        exchangeValues.first {
            it.currencyId == currencyId
        }.fav = fav
    }

    fun addFakeExchangeValues() {
        for (i in 0..5) {
            exchangeValues.add(getRandomExchangeValue())
        }
    }

    private val platformsWithFavExchangeValues = listOf(
        ExchangePlatformType.Binance,
        ExchangePlatformType.Buenbit
    )

    fun addFakeFavExchangeValues() {
        platformsWithFavExchangeValues.forEach {
            val favExchangeValue = ExchangeValue(
                fav = true,
                platform = it,
                exchangeValue = (1..10).random().toDouble()
            )
            exchangeValues.add(favExchangeValue)
        }

        // Add some not fav exchange values to know if UseCase filters are working correctly
        for (i in 0..3) {
            exchangeValues.add(
                getRandomExchangeValue()
                    .copy(
                        platform = ExchangePlatformType.Binance
                    )
            )
        }

    }

    fun addFakePlatformUpdates() {
        listOf(
            ExchangePlatformType.Binance,
            ExchangePlatformType.Buenbit,
            ExchangePlatformType.Ripio
        ).forEach {
            platformUpdates.add(
                TradingPlatformUpdates(
                    platformType = it
                )
            )
        }
    }

    private fun getRandomExchangeValue(): ExchangeValue {
        return ExchangeValue(
            platform = getRandomPlatform(),
            exchangeValue = (1..10).random().toDouble()
        )
    }

    private fun getRandomPlatform(): ExchangePlatformType {
        return getPlatforms().random()
    }

    private fun getPlatforms() = listOf(
        ExchangePlatformType.Binance,
        ExchangePlatformType.Buenbit,
        ExchangePlatformType.Ripio
    )

}