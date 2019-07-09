package com.simiomobile.masterdetail.utils.extension

import java.text.NumberFormat
import java.util.*

fun Double.currencyFormat(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}