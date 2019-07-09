package com.simiomobile.masterdetail.data.repository.local.preference

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simiomobile.masterdetail.data.local.database.CoinsDao
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.data.local.preference.SharedPreference
import io.reactivex.Completable
import io.reactivex.Single

private const val PREF_FAVORITE_COINS = "FavoriteCoinsIds"

interface FavoriteCoinsIdLocalRepository {
    fun getFavoriteCoinsIds(): Single<List<String>>
    fun setFavoriteCoinsIds(coinsIds: List<String>): Completable
}

class FavoriteCoinsIdLocalRepositoryImpl(private val sharedPreference: SharedPreference) :
    FavoriteCoinsIdLocalRepository {
    override fun getFavoriteCoinsIds(): Single<List<String>> {
        return Single.just(
            Gson().fromJson(
                sharedPreference.get(PREF_FAVORITE_COINS),
                object : TypeToken<List<String>>() {}.type
            ) ?: listOf<String>()
        )
    }

    override fun setFavoriteCoinsIds(coinsIds: List<String>): Completable {
        return Completable.create { emitter ->
            sharedPreference.put(PREF_FAVORITE_COINS, Gson().toJson(coinsIds))
            emitter.onComplete()
        }
    }
}