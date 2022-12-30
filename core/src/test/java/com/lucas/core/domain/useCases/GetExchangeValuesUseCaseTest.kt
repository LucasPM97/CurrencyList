package com.lucas.core.domain.useCases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.TradingPlatformUpdates
import com.lucas.core.data.repositories.IExchangeRepository
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class GetExchangeValuesUseCaseTest {

    private lateinit var getExchangeValues: GetExchangeValuesUseCase
    private val repository: IExchangeRepository = mockk()

    @Before
    fun setup() {
        clearMocks(repository)
        getExchangeValues = GetExchangeValuesUseCase(repository)
    }

    @Test
    fun `Get exchange values grouped by Platform`() = runBlocking {

        every { repository.getExchangeValues() } returns flow {
            emit(fakeExchangeValues())
        }
        every { repository.getPlatformsLastUpdateFlow() } returns flow {
            emit(fakePlatformUpdates())
        }

        getExchangeValues().test {

            val platformStates = awaitItem()
            platformStates.forEach { platformState ->

                val platformsFromExchangeValues = platformState.exchangeValues.map {
                    it.platform
                }

                val anyDifferentPlatform = platformsFromExchangeValues.any {
                    it != platformState.platformType
                }

                assertThat(anyDifferentPlatform).isFalse()
                awaitComplete()
            }
        }
    }

    @Test
    fun `Even without exchange values, platforms must be returned`() = runBlocking {

        every { repository.getExchangeValues() } returns flow {
            emit(emptyList())
        }
        every { repository.getPlatformsLastUpdateFlow() } returns flow {
            emit(fakePlatformUpdates())
        }

        getExchangeValues().test {
            val platformStates = awaitItem()

            platformStates.forEach { platformState ->

                val anyExchangeValues = platformState.exchangeValues.any()

                assertThat(anyExchangeValues).isFalse()
                awaitComplete()
            }
        }
    }

    // region Fake data

    private fun fakeExchangeValues(): List<ExchangeValue> = (0..5).map {
        mockk {
            every { platform } returns getPlatforms().random()
        }
    }

    private fun fakePlatformUpdates(): List<TradingPlatformUpdates> = getPlatforms().map {
        mockk {
            every { platformType } returns it
            every { lastUpdate } returns Date()
        }
    }

    private fun getPlatforms() = listOf(
        ExchangePlatformType.Binance,
        ExchangePlatformType.Buenbit,
        ExchangePlatformType.Ripio
    )
    //endregion
}