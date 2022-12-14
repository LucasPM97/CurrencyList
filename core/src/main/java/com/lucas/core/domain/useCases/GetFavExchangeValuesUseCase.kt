package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.ICurrencyRepository
import com.lucas.core.domain.extensions.anyPlatform
import com.lucas.core.domain.extensions.filterByPlatform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface IGetFavExchangeValuesUseCase {
    operator fun invoke(): Flow<List<PlatformState>>
}

class GetFavExchangeValuesUseCase(
    private val repository: ICurrencyRepository
) : IGetFavExchangeValuesUseCase {

    override operator fun invoke(): Flow<List<PlatformState>> {

        val platformsLastUpdateFlow = repository.getPlatformsLastUpdateFlow()
        val exchangeValuesFlow = repository.getFavExchangeValues()

        return combine(
            exchangeValuesFlow,
            platformsLastUpdateFlow
        ) { exchangeValues, platformsLastUpdate ->

            platformsLastUpdate.filter {
                exchangeValues.anyPlatform(it.platformType)
            }.map { platform ->
                PlatformState(
                    platformType = platform.platformType,
                    lastUpdate = platform.lastUpdate,
                    exchangeValues = exchangeValues.filterByPlatform(platform.platformType)
                )
            }
        }
    }
}