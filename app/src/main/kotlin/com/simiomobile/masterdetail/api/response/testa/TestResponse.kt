package com.simiomobile.masterdetail.api.response.testa

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class TestResponse(

	@field:SerializedName("price_change_percentage_24h")
	val priceChangePercentage24h: Double? = null,

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("circulating_supply")
	val circulatingSupply: Double? = null,

	@field:SerializedName("total_volume")
	val totalVolume: Long? = null,

	@field:SerializedName("price_change_24h")
	val priceChange24h: Double? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("total_supply")
	val totalSupply: Double? = null,

	@field:SerializedName("market_cap_rank")
	val marketCapRank: Int? = null,

	@field:SerializedName("roi")
	val roi: Any? = null,

	@field:SerializedName("market_cap_change_24h")
	val marketCapChange24h: Double? = null,

	@field:SerializedName("market_cap")
	val marketCap: Long? = null,

	@field:SerializedName("ath")
	val ath: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("high_24h")
	val high24h: Int? = null,

	@field:SerializedName("low_24h")
	val low24h: Int? = null,

	@field:SerializedName("market_cap_change_percentage_24h")
	val marketCapChangePercentage24h: Double? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("current_price")
	val currentPrice: Int? = null,

	@field:SerializedName("ath_change_percentage")
	val athChangePercentage: Double? = null,

	@field:SerializedName("ath_date")
	val athDate: String? = null
)