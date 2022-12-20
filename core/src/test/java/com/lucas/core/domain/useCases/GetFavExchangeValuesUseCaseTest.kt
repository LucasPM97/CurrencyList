package com.lucas.core.domain.useCases

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.lucas.core.mock.data.repositories.FakeExchangeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavExchangeValuesUseCaseTest {

    private lateinit var getFavExchangeValues: GetFavExchangeValuesUseCase
    private lateinit var repository: FakeExchangeRepository

    @Before
    fun setup() {
        repository = FakeExchangeRepository()
        getFavExchangeValues = GetFavExchangeValuesUseCase(repository)
    }

    @Test
    fun `Get Fav exchange values grouped by Platform`() = runBlocking {
        repository.addFakeFavExchangeValues()
        repository.addFakePlatformUpdates()

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
    }

    @Test
    fun `Return only platforms with favored exchange values`() = runBlocking {
        repository.addFakeFavExchangeValues()
        repository.addFakePlatformUpdates()

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
}