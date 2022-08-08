package com.adhishlal.mygithubprs.domain.utils

import android.annotation.SuppressLint
import com.adhishlal.mygithubprs.data.utils.Constants
import java.text.ParseException
import java.text.SimpleDateFormat

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun tzToDateString(dateString: String): String {
        val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val stringDateFormat = SimpleDateFormat("MMMM dd, uuuu")
        val yourDesiredFormat = SimpleDateFormat("dd-MMM-yyyy")
        var dateStringToReturn = Constants.BLANK
        try {
            val date = zonedFormat.parse(dateString)
            dateStringToReturn = date?.let { yourDesiredFormat.format(it) }.toString()
        } catch (e: ParseException) {
            try {
                val date = stringDateFormat.parse(dateString)
                dateStringToReturn = date?.let { yourDesiredFormat.format(it) }.toString()
            } catch (e: ParseException) {
                e.localizedMessage
            }
        }

        return dateStringToReturn
    }
}