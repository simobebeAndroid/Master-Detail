package com.simiomobile.masterdetail.data.repository.api

import com.simiomobile.masterdetail.api.MasterDetailApi
import com.simiomobile.masterdetail.api.response.allcoins.CoinsItem
import io.reactivex.Single

interface CoinsApiRepository {
    fun getAllCoinsWithMarket(): Single<List<CoinsItem?>?>
}

class CoinsApiRepositoryImpl(private val remote: MasterDetailApi) :
    CoinsApiRepository {
    override fun getAllCoinsWithMarket(): Single<List<CoinsItem?>?> {
        return remote.getAllCoins()
    }
}