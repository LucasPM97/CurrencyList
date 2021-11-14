package com.lucas.core.utils.extensions

import android.os.Build
import com.lucas.core.utils.helpers.DetailedDate
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.toDetailedDate(): DetailedDate {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val localDate: LocalDate = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        return DetailedDate(
            year = localDate.year,
            month = localDate.monthValue,
            day = localDate.dayOfMonth,
        )

    } else {
        val cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"))
        cal.setTime(this)
        return DetailedDate(
            year = cal[Calendar.YEAR],
            month = cal[Calendar.MONTH],
            day = cal[Calendar.DAY_OF_MONTH]
        )
    }
}

fun Date.toString(pattern: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val localDate: LocalDateTime = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        val formatters = DateTimeFormatter.ofPattern(pattern)
        localDate.format(formatters)
    } else {
        val simpleDateFormat = SimpleDateFormat(pattern)
        simpleDateFormat.format(this)
    }
}
