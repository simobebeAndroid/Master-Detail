package com.simiomobile.masterdetail.utils.extension

import com.simiomobile.masterdetail.api.response.allcoins.CoinsItem
import com.simiomobile.masterdetail.data.local.model.CoinsData

fun CoinsItem.applyToUse(): CoinsData {
    return CoinsData(
        id = null,
        coinsId = this.id ?: "",
        name = this.name ?: "",
        image = this.image ?: "",
        currentPrice = this.currentPrice ?: 0.0,
        marketCap = this.marketCap ?: 0.0,
        marketCapRank = this.marketCapRank ?: 0,
        high24h = this.high24h?.toString() ?: "",
        low24h = this.low24h?.toString() ?: "",
        roiTime = this.roi?.times?.toString() ?: "",
        roiCurrency = this.roi?.currency ?: "",
        roiPercentage = this.roi?.percentage?.toString() ?: "",
        totalVolume = this.totalVolume?.toString() ?: "",
        lastUpdated = this.lastUpdated ?: ""
    )
}