package com.simiomobile.masterdetail.api.response.allcoins

import com.google.gson.annotations.SerializedName

data class CoinsItem(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("ath")
    val ath: Double? = null,
    @field:SerializedName("ath_change_percentage")
    val athChangePercentage: Double? = null,
    @field:SerializedName("ath_date")
    val athDate: String? = null,
    @field:SerializedName("circulating_supply")
    val circulatingSupply: Double? = null,
    @field:SerializedName("current_price")
    val currentPrice: Double? = null,
    @field:SerializedName("image")
    val image: String? = null,
    @field:SerializedName("high_24h")
    val high24h: Double? = null,
    @field:SerializedName("low_24h")
    val low24h: Double? = null,
    @field:SerializedName("market_cap")
    val marketCap: Int? = null,
    @field:SerializedName("market_cap_change_24h")
    val marketCapChange24h: Int? = null,
    @field:SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double? = null,
    @field:SerializedName("market_cap_rank")
    val marketCapRank: Int? = null,
    @field:SerializedName("price_change_24h")
    val priceChange24h: Double? = null,
    @field:SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null,
    @field:SerializedName("roi")
    val roi: Roi? = null,
    @field:SerializedName("symbol")
    val symbol: String? = null,
    @field:SerializedName("total_supply")
    val totalSupply: Double? = null,
    @field:SerializedName("total_volume")
    val totalVolume: Int? = null,
    @field:SerializedName("lastUpdated")
    val lastUpdated: String? = null
)