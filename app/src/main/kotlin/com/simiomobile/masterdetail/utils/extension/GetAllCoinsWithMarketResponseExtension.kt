package com.simiomobile.masterdetail.utils.extension

import com.simiomobile.masterdetail.api.response.allcoins.CoinsItem
import com.simiomobile.masterdetail.data.local.model.CoinsData

fun List<CoinsItem?>?.applyToUse(): List<CoinsData> {
    return this?.mapNotNull {
        it?.applyToUse()
    } ?: listOf()
}