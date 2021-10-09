package com.lucas.currencylist.models

import kotlinx.coroutines.flow.Flow

data class TradingWebProvider (
    val state : Flow<TradingWeb>
)