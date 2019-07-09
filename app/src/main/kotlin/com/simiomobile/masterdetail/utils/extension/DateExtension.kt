package com.simiomobile.masterdetail.utils.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.changeDateFormat(): String{
    return if (this.isEmpty()) {
        ""
    } else {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US)
        val date: Date = inputFormat.parse(this)
        outputFormat.format(date)
    }
}