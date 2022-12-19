package com.lucas.core.domain.useCases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.lucas.core.mock.data.repositories.FakeExchangeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExchangeValuesUseCaseTest {

    private lateinit var getExchangeValues: GetExchangeValuesUseCase
    private lateinit var repository: FakeExchangeRepository

    @Before
    fun setup() {
        repository = FakeExchangeRepository()
        getExchangeValues = GetExchangeValuesUseCase(repository)
    }

    @Test
    fun `Get Platform States`() = runBlocking {
        repository.addFakeExchangeValues()
        repository.addFakePlatformUpdates()

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
    fun `All platforms returned but exchangeValues as emptyList `() = runBlocking {
        repository.addFakePlatformUpdates()

        getExchangeValues().test {
            val platformStates = awaitItem()

            platformStates.forEach { platformState ->

                val anyExchangeValues = platformState.exchangeValues.any()

                assertThat(anyExchangeValues).isFalse()
                awaitComplete()
            }
        }
    }
}