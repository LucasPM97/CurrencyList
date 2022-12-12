package com.lucas.currencylist.ui.extensions

import android.content.Context
import com.lucas.core.utils.extensions.toDetailedDate
import com.lucas.core.utils.extensions.toString
import com.lucas.core.utils.helpers.DateHelper
import com.lucas.currencylist.R
import java.util.Date

fun Date.lastUpdateText(context: Context): String {
    val currentDate = DateHelper.currentDate()
    val currentDetailedDate = currentDate.toDetailedDate()
    val lastUpdateDetailedDate = this.toDetailedDate()

    if (lastUpdateDetailedDate.day == currentDetailedDate.day) {
        return this.toString("HH:mm")
    } else
        if (currentDetailedDate.day == (lastUpdateDetailedDate.day - 1)) {
            return context.getString(R.string.yesterday) + this.toString("HH:mm")
        }

    return this.toString("MMM dd")
}