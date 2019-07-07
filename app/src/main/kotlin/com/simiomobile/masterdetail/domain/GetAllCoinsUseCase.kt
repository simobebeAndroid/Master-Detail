package com.simiomobile.masterdetail.domain

import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepository
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.data.repository.local.CoinsLocalRepository
import com.simiomobile.masterdetail.utils.extension.applyToUse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface GetAllCoinsUseCase {
    fun execute(): Single<List<CoinsData>>
}

class GetAllCoinsUseCaseImpl(
    private val coinsApiRepository: CoinsApiRepository,
    private val coinsLocalRepository: CoinsLocalRepository
) : GetAllCoinsUseCase {
    override fun execute(): Single<List<CoinsData>> {
        return coinsApiRepository.getAllCoinsWithMarket()
            .observeOn(Schedulers.io())
            .flatMap { getCoinsResponse ->
                if (getCoinsResponse.data?.isNotEmpty() == true) {
                    coinsLocalRepository.saveAllCoinsList(getCoinsResponse.applyToUse()).toSingle {
                        coinsLocalRepository.getAllCoins()
                    }.flatMap {
                        it
                    }
                } else {
                    Single.just(listOf<CoinsData>())
                }
            }
    }

}