package com.lucas.core.models.utils.extensions

import java.text.DecimalFormat

fun Double.roundString(): String =
    DecimalFormat("#.##").format(this)
