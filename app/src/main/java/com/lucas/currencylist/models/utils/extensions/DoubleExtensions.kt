package com.lucas.currencylist.models.utils.extensions

import java.text.DecimalFormat

fun Double.roundString(): String =
    DecimalFormat("#.##").format(this)
