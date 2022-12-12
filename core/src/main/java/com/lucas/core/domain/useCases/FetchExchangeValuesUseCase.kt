package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.ICurrencyRepository

interface IFetchExchangeValuesUseCase {
    suspend operator fun invoke(): Boolean
}

class FetchExchangeValuesUseCase(
    private val repository: ICurrencyRepository
) : IFetchExchangeValuesUseCase {

    override suspend operator fun invoke(): Boolean =
        repository.fetchExchangeValues()
}