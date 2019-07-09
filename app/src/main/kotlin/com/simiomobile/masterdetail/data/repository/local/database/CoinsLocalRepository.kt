package com.simiomobile.masterdetail.data.repository.local.database

import com.simiomobile.masterdetail.data.local.database.CoinsDao
import com.simiomobile.masterdetail.data.local.model.CoinsData
import io.reactivex.Completable
import io.reactivex.Single

interface CoinsLocalRepository {
    fun getAllCoins(): Single<List<CoinsData>>
    fun getCoinsById(coinsId: String): Single<CoinsData>
    fun updateCoinsList(listCoinsData: List<CoinsData>): Completable
    fun saveAllCoinsList(listCoinsData: List<CoinsData>): Completable
    fun saveCoins(coinsData: CoinsData): Completable
    fun deleteCoinById(coinsId: String): Completable
    fun deleteAllCoins(): Completable
}

class CoinsLocalRepositoryImpl(private val coinsDao: CoinsDao) :
    CoinsLocalRepository {
    override fun getAllCoins(): Single<List<CoinsData>> {
        return coinsDao.getAllCoins().first(listOf())
    }

    override fun getCoinsById(coinsId: String): Single<CoinsData> {
        return coinsDao.getCoinsById(coinsId)
    }

    override fun updateCoinsList(listCoinsData: List<CoinsData>): Completable {
        return Completable.create { emitter ->
            coinsDao.updateData(listCoinsData)
            emitter.onComplete()
        }
    }

    override fun saveAllCoinsList(listCoinsData: List<CoinsData>): Completable {
        return Completable.create { emitter ->
            coinsDao.insertAll(listCoinsData)
            emitter.onComplete()
        }
    }

    override fun saveCoins(coinsData: CoinsData): Completable {
        return Completable.create { emitter ->
            coinsDao.insert(coinsData)
            emitter.onComplete()
        }
    }

    override fun deleteCoinById(coinsId: String): Completable {
        return Completable.create { emitter ->
            coinsDao.deleteById(coinsId)
            emitter.onComplete()
        }
    }

    override fun deleteAllCoins(): Completable {
        return Completable.create { emitter ->
            coinsDao.deleteByAll()
            emitter.onComplete()
        }
    }

}