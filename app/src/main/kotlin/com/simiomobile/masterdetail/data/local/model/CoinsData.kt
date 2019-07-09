package com.simiomobile.masterdetail.data.local.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "coins_favorite_data")
data class CoinsData(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "coins_id")
    var coinsId: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "image")
    var image: String = "",

    @ColumnInfo(name = "current_price")
    var currentPrice: Double = 0.0,

    @ColumnInfo(name = "market_cap")
    var marketCap: Double = 0.0,

    @ColumnInfo(name = "market_cap_rank")
    var marketCapRank: Int = 0,

    @ColumnInfo(name = "high_24h")
    var high24h: String = "",

    @ColumnInfo(name = "low_24h")
    var low24h: String = "",

    @ColumnInfo(name = "roi_times")
    var roiTime: String = "",

    @ColumnInfo(name = "roi_currency")
    var roiCurrency: String = "",

    @ColumnInfo(name = "roi_percentage")
    var roiPercentage: String = "",

    @ColumnInfo(name = "total_volume")
    var totalVolume: String = "",

    @ColumnInfo(name = "last_updated")
    var lastUpdated: String = "",

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)