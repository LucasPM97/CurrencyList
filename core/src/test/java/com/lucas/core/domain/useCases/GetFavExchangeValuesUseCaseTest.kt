package com.lucas.core.domain.useCases

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.lucas.core.data.models.ExchangePlatformType
import com.lucas.core.data.models.ExchangeValue
import com.lucas.core.data.models.TradingPlatformUpdates
import com.lucas.core.data.repositories.IExchangeRepository
import com.lucas.core.mock.data.repositories.FakeExchangeRepository
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavExchangeValuesUseCaseTest {

    private lateinit var getFavExchangeValues: GetFavExchangeValuesUseCase
    private val repository: IExchangeRepository = mockk()

    @Before
    fun setup() {
        getFavExchangeValues = GetFavExchangeValuesUseCase(repository)
        clearMocks(repository)
    }

    @Test
    fun `Get Fav exchange values grouped by Platform`() = runBlocking {

        coEvery { repository.getFavExchangeValues() } returns flow {
            emit(fakeFavExchangeValues())
        }
        coEvery { repository.getPlatformsLastUpdateFlow() } returns flow {
            emit(fakePlatformUpdates())
        }

        getFavExchangeValues().test {
            val platformStates = awaitItem()
            platformStates.forEach { platformState ->

                val platformsFromExchangeValues = platformState.exchangeValues.map {
                    it.platform
                }

                val anyDifferentPlatform = platformsFromExchangeValues.any {
                    it != platformState.platformType
                }

                Truth.assertThat(anyDifferentPlatform).isFalse()
                awaitComplete()
            }
        }

        coVerify { repository.getFavExchangeValues() }
        coVerify { repository.getPlatformsLastUpdateFlow() }
    }

    @Test
    fun `Return only platforms with favored exchange values`() = runBlocking {

        coEvery { repository.getFavExchangeValues() } returns flow {
            emit(fakeFavExchangeValues())
        }
        coEvery { repository.getPlatformsLastUpdateFlow() } returns flow {
            emit(fakePlatformUpdates())
        }

        getFavExchangeValues().test {
            val platformStates = awaitItem()

            platformStates.forEach { platformState ->

                val anyNotFavExchangeValues = platformState.exchangeValues.any {
                    it.fav == false
                }

                Truth.assertThat(anyNotFavExchangeValues).isFalse()
                awaitComplete()
            }
        }
    }

    // region Fake data
    private val platformsWithFavExchangeValues = listOf(
        ExchangePlatformType.Binance,
        ExchangePlatformType.Buenbit
    )

    private fun fakeFavExchangeValues(): List<ExchangeValue> {
        val fakeExchangeValueList =
            platformsWithFavExchangeValues.map<ExchangePlatformType, ExchangeValue> {
                mockk {
                    every { fav } returns true
                    every { platform } returns it
                }
            }.toMutableList()

        // Add some not fav exchange values to know if UseCase filters are working correctly
        for (i in 0..3) {
            fakeExchangeValueList.add(
                mockk {
                    every { fav } returns false
                    every { platform } returns ExchangePlatformType.Binance
                }
            )
        }

        return fakeExchangeValueList
    }

    private fun fakePlatformUpdates(): List<TradingPlatformUpdates> = listOf(
        ExchangePlatformType.Binance,
        ExchangePlatformType.Buenbit,
        ExchangePlatformType.Ripio
    ).map {
        mockk {
            every { platformType } returns it
        }
    }
    //endregion
}