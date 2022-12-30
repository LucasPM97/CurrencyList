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
    private val repository: IExchangeRepository = mockk {
        coEvery { updateExchangeValueFav(any(), any()) } returns Unit
    }

    @Before
    fun setup() {
        updateExchangeValueFav = UpdateExchangeValueFavUseCase(repository)
    }

    @Test
    fun `Update exchange value and check if repository UpdateFav method was called`() =
        runBlocking {
            updateExchangeValueFav("", true)

            coVerify { repository.updateExchangeValueFav(any(), any()) }
        }
}