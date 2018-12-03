package test.alexevtushik.justapptest.presentation.utils

import android.annotation.SuppressLint
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(date: String): String {
        val parsedDate: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(date)
        val simpleDateFormat = SimpleDateFormat("MMM dd, h:mma")
        val symbols = DateFormatSymbols(Locale.getDefault())
        symbols.amPmStrings = arrayOf("am", "pm")
        simpleDateFormat.dateFormatSymbols = symbols
        return simpleDateFormat.format(parsedDate)
    }
}