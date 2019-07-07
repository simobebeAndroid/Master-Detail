package com.simiomobile.masterdetail.data.local

import android.arch.persistence.room.*
import com.simiomobile.masterdetail.data.local.model.CoinsData
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CoinsDao {
    @Query("SELECT * FROM coins_favorite_data")
    fun getAllCoins(): Flowable<List<CoinsData>>

    @Query("SELECT * FROM coins_favorite_data WHERE coins_id = :id")
    fun getCoinsById(id: String): Single<CoinsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteData: CoinsData)

    @Delete
    fun delete(favoriteData: CoinsData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFavorite: List<CoinsData>)

    @Transaction
    open fun updateData(listFavorite: List<CoinsData>) {
        deleteByAll()
        insertAll(listFavorite)
    }

    @Query("DELETE FROM coins_favorite_data WHERE coins_id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM coins_favorite_data ")
    fun deleteByAll()
}