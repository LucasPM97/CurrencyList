package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.IExchangeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IUpdateExchangeValueFavUseCase {
    suspend operator fun invoke(currencyId: String, fav: Boolean)
}

class UpdateExchangeValueFavUseCase(
    private val repository: IExchangeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IUpdateExchangeValueFavUseCase {

    override suspend operator fun invoke(currencyId: String, fav: Boolean) =
        withContext(dispatcher) {
            repository.updateExchangeValueFav(currencyId, fav)
        }
}