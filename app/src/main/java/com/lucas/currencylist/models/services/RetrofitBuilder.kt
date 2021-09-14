package com.lucas.currencylist.models.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BUENBIT_BASE_URL = "https://be.buenbit.com/api/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BUENBIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val buenbitService: BuenbitService = getRetrofit().create(BuenbitService::class.java)
}