package com.eegrab.habittrackermvvm.utils

import android.provider.ContactsContract
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object Calculations {

    fun calculateTimeBetweenDates(startDate: String): String {

        val endDate = timeStampToString(System.currentTimeMillis())

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date1 = sdf.parse(startDate)
        val date2 = sdf.parse(endDate)

        var isNegative = false

        var difference = date2.time - date1.time

        if (difference < 0 ) {
            difference = -(difference)
            isNegative = true
        }

        val minutes = difference / 60 / 1000
        val hours = difference / 60 / 1000 / 60
        val days = (difference / 60 / 1000 / 60) / 24
        val months = (difference / 60 / 1000 / 60) / 24 / (365/12)
    }

    private fun timeStampToString(timeStamp: Long) : String  {
        val stamp  = Timestamp(timeStamp)
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val date = sdf.format((Date(stamp.time)))

        return date.toString()
    }
}