package com.lucas.core.domain.useCases

import com.lucas.core.data.repositories.IExchangeRepository

interface IFetchExchangeValuesUseCase {
    suspend operator fun invoke(): Boolean
}

class FetchExchangeValuesUseCase(
    private val repository: IExchangeRepository
) : IFetchExchangeValuesUseCase {

    override suspend operator fun invoke(): Boolean =
        repository.fetchExchangeValues()
}