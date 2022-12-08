package com.lucas.core.domain.useCases

import com.lucas.core.data.models.CurrencyValue
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.repositories.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.Date

interface IGetExchangeValuesUseCase {
    operator fun invoke(): Flow<List<PlatformState>>
}

class GetExchangeValuesUseCase(
    private val repository: ICurrencyRepository
) : IGetExchangeValuesUseCase {

    override operator fun invoke(): Flow<List<PlatformState>> {

        val platformsLastUpdateFlow = repository.getPlatformsLastUpdateFlow()
        val exchangeValuesFlow = repository.getExchangeValues()

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

data class PlatformState(
    val platformType: ExchangePlatformType,
    val exchangeValues: List<CurrencyValue>,
    val lastUpdate: Date
)