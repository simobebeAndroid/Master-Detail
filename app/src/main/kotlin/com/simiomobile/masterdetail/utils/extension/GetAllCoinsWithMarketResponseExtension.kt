package com.simiomobile.masterdetail.utils.extension

import com.simiomobile.masterdetail.api.response.allcoins.GetAllCoinsWithMarketResponse
import com.simiomobile.masterdetail.data.local.model.CoinsData

fun GetAllCoinsWithMarketResponse.applyToUse(): List<CoinsData> {
    return this.data?.mapNotNull {
        it?.applyToUse()
    } ?: listOf()
}