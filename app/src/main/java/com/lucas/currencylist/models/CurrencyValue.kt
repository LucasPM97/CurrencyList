package com.lucas.currencylist.models

data class CurrencyValue(
    val title: String,
    val exchangeValue : Float,
    val type : CurrencyType
){
    fun getImageResource(): Int {
        when(type){
            CurrencyType.ARStoDAI -> TODO("Get ARS to DAI Image")
            CurrencyType.USDtoBTC -> TODO("Get ARS to DAI Image")
            CurrencyType.USDtoETH -> TODO("Get ARS to DAI Image")
            else -> TODO("Show Gray circle")
        }
    }
}

enum class CurrencyType{
    ARStoDAI,
    USDtoBTC,
    USDtoETH
}