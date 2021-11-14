package com.lucas.core.utils.helpers

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object DateHelper {
    fun currentDate(): Date {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDate = currentLocalDateTime()
            return Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant())
        } else {
            return currentCalendarTime()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentLocalDateTime(): LocalDateTime = LocalDateTime.now()

    fun currentCalendarTime(): Date = Calendar.getInstance().time
}



data class DetailedDate(
    val year : Int,
    val month : Int,
    val day : Int,
)