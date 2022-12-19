package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.IExchangeRepository
import com.lucas.core.domain.extensions.anyPlatform
import com.lucas.core.domain.extensions.filterByPlatform
import com.lucas.core.domain.extensions.toPlatformState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface IGetFavExchangeValuesUseCase {
    operator fun invoke(): Flow<List<PlatformState>>
}

class GetFavExchangeValuesUseCase(
    private val repository: IExchangeRepository
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
                platform.toPlatformState(exchangeValues)

            }
        }
    }
}