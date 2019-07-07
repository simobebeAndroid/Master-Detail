package com.simiomobile.masterdetail.utils.extension

import java.text.NumberFormat

fun Long.currencyFormat(): String {
    return NumberFormat.getNumberInstance().format(this)
}