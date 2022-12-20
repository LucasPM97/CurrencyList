package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.IExchangeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateExchangeValueFavUseCaseTest {

    private lateinit var updateExchangeValueFav: UpdateExchangeValueFavUseCase
    private lateinit var repository: IExchangeRepository

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        updateExchangeValueFav = UpdateExchangeValueFavUseCase(repository)
    }

    @Test
    fun `Update exchange value and check if repository was called`() = runBlocking {

        coEvery { repository.updateExchangeValueFav(any(), any()) } returns Unit

        updateExchangeValueFav("", true)

        coVerify { repository.updateExchangeValueFav(any(), any()) }
        confirmVerified(repository)
    }
}