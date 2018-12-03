package test.alexevtushik.justapptest.presentation.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan

object StringFormatter {

    fun getFormattedBoldName(template: String, firstName: String, lastName: String): CharSequence {
        val title = String.format(template, firstName, lastName)
        val spannableString = SpannableString(title)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            firstName.length + lastName.length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString;
    }
}