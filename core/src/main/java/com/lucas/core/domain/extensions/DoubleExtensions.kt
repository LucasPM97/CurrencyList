package com.lucas.core.domain.extensions

import java.text.DecimalFormat

fun Double.roundString(): String =
    DecimalFormat("#.##").format(this)
