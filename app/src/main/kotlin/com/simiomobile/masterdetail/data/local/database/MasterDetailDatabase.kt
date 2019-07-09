package com.simiomobile.masterdetail.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.simiomobile.masterdetail.data.local.model.CoinsData


@Database(entities = [CoinsData::class], version = 1)
abstract class MasterDetailDatabase : RoomDatabase(){
    abstract fun coinsDao() : CoinsDao
}