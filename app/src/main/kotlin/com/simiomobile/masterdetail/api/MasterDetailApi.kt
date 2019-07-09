package com.simiomobile.masterdetail.api

import com.simiomobile.masterdetail.api.response.allcoins.CoinsItem
import com.simiomobile.masterdetail.api.response.allcoins.GetAllCoinsWithMarketResponse
import io.reactivex.Single
import retrofit2.http.*


interface MasterDetailApi {
    @GET(GET_ALL_COINS)
    fun getAllCoins(
        @Query("vs_currency") currency: String = "usd"
    ): Single<List<CoinsItem?>?>
}