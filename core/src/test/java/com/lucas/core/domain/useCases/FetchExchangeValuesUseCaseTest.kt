package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.IExchangeRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchExchangeValuesUseCaseTest {

    private lateinit var fetchExchangeValues: FetchExchangeValuesUseCase
    private lateinit var repository: IExchangeRepository

    @Before
    fun setup() {
        repository = mockk {
            coEvery { fetchExchangeValues() } returns true
        }
        fetchExchangeValues = FetchExchangeValuesUseCase(repository)
    }

    @Test
    fun `Run Fetch use case and check if repository FetchExchangeValues method was called`() =
        runBlocking {

            fetchExchangeValues()

            coVerify { repository.fetchExchangeValues() }
            confirmVerified(repository)
        }
}