package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetFavExchangeValuesUseCase(
    private val repository: ICurrencyRepository
) : IGetExchangeValuesUseCase {

    override operator fun invoke(): Flow<List<PlatformState>> {

        val platformsLastUpdateFlow = repository.getPlatformsLastUpdateFlow()
        val exchangeValuesFlow = repository.getFavExchangeValues()

        return combine(
            exchangeValuesFlow,
            platformsLastUpdateFlow
        ) { exchangeValues, platformsLastUpdate ->
            platformsLastUpdate.map { platform ->
                PlatformState(
                    platformType = platform.platformType,
                    lastUpdate = platform.lastUpdate,
                    exchangeValues = exchangeValues.filter {
                        it.platform == platform.platformType
                    }
                )
            }
        }
    }
}