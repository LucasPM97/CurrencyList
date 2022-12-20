package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.IExchangeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchExchangeValuesUseCaseTest {

    private lateinit var fetchExchangeValues: FetchExchangeValuesUseCase
    private lateinit var repository: IExchangeRepository

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        fetchExchangeValues = FetchExchangeValuesUseCase(repository)
    }

    @Test
    fun `Run Fetch use case and check if repository FetchExchangeValues method was called`() =
        runBlocking {

            coEvery { repository.fetchExchangeValues() } returns true

            fetchExchangeValues()

            coVerify { repository.fetchExchangeValues() }
            confirmVerified(repository)
        }
}