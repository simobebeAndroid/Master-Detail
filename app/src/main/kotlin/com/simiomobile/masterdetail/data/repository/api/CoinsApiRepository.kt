package com.simiomobile.masterdetail.data.repository.api

import com.simiomobile.masterdetail.api.MasterDetailApi
import com.simiomobile.masterdetail.api.response.allcoins.GetAllCoinsWithMarketResponse
import io.reactivex.Single

interface CoinsApiRepository {
    fun getAllCoinsWithMarket(): Single<GetAllCoinsWithMarketResponse>
}

class CoinsApiRepositoryImpl(private val remote: MasterDetailApi) :
    CoinsApiRepository {
    override fun getAllCoinsWithMarket(): Single<GetAllCoinsWithMarketResponse> {
        return remote.getAllCoins()
    }
}