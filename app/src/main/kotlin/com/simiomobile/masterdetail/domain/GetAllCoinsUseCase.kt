package com.simiomobile.masterdetail.domain

import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepository
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.data.repository.local.database.CoinsLocalRepository
import com.simiomobile.masterdetail.data.repository.local.preference.FavoriteCoinsIdLocalRepository
import com.simiomobile.masterdetail.utils.extension.applyToUse
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

interface GetAllCoinsUseCase {
    fun execute(): Single<List<CoinsData>>
}

class GetAllCoinsUseCaseImpl(
    private val coinsApiRepository: CoinsApiRepository,
    private val coinsLocalRepository: CoinsLocalRepository,
    private val favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository
) : GetAllCoinsUseCase {
    override fun execute(): Single<List<CoinsData>> {
        return coinsApiRepository.getAllCoinsWithMarket()
            .observeOn(Schedulers.io())
            .flatMap { listCoins ->
                if (listCoins.isNotEmpty()) {
                    coinsLocalRepository.updateCoinsList(listCoins.applyToUse())
                        .andThen(coinsLocalRepository.getAllCoins())
                        .zipWith(favoriteCoinsIdLocalRepository.getFavoriteCoinsIds(),
                            BiFunction { coins, favoriteIds ->
                                coins.map { coin ->
                                    if (favoriteIds.contains(coin.coinsId)) {
                                        coin.isFavorite = true
                                    }
                                    coin
                                }.sortedBy {
                                    it.marketCapRank
                                }
                            })
                } else {
                    Single.just(listOf<CoinsData>())
                }
            }
    }

}