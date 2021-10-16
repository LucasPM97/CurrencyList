package com.lucas.core.utils.extensions

import java.text.DecimalFormat

fun Double.roundString(): String =
    DecimalFormat("#.##").format(this)
