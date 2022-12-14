package com.lucas.currencylist.ui.screens.favExchangeList

import androidx.lifecycle.ViewModel
import com.lucas.core.domain.useCases.IGetFavExchangeValuesUseCase
import com.lucas.core.domain.useCases.IUpdateExchangeValueFavUseCase

class FavExchangeListViewModel(
    private val getFavExchangeValues: IGetFavExchangeValuesUseCase,
    private val updateExchangeValueFav: IUpdateExchangeValueFavUseCase
) : ViewModel() {

    val currencies = getFavExchangeValues()

//    private val workManager = WorkManager.getInstance(application)
//
//    init {
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresBatteryNotLow(true)
//            .build()
//        val request = PeriodicWorkRequestBuilder<ExchangeFetchWorker>(15, TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .addTag(ExchangeFetchWorker.TAG)
//            .build()
//        workManager.enqueueUniquePeriodicWork(
//            ExchangeFetchWorker.TAG,
//            ExistingPeriodicWorkPolicy.REPLACE,
//            request
//        )
//    }

    suspend fun updateFav(currencyId: String, fav: Boolean) {
        updateExchangeValueFav(currencyId, fav)
    }
}