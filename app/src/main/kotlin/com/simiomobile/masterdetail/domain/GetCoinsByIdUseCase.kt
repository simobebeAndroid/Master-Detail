package com.simiomobile.masterdetail.domain

import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.data.repository.local.database.CoinsLocalRepository
import com.simiomobile.masterdetail.data.repository.local.preference.FavoriteCoinsIdLocalRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

interface GetCoinsByIdUseCase {
    fun execute(coinId: String): Single<CoinsData>
}

class GetCoinsByIdUseCaseImpl(
    private val coinsLocalRepository: CoinsLocalRepository,
    private val favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository
) : GetCoinsByIdUseCase {
    override fun execute(coinId: String): Single<CoinsData> {
        return coinsLocalRepository.getCoinsById(coinId).zipWith(favoriteCoinsIdLocalRepository.getFavoriteCoinsIds(),
            BiFunction { coin, favoriteIds ->
                if (favoriteIds.contains(coin.coinsId)) {
                    coin.isFavorite = true
                }
                coin
            })
    }
}