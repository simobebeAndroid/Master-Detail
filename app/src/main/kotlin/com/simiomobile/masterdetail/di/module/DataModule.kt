package com.simiomobile.masterdetail.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.simiomobile.masterdetail.data.local.CoinsDao
import com.simiomobile.masterdetail.data.local.MasterDetailDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


private const val DATABASE_NAME = "master_detail.db"

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMasterDetailDatabase(): Class<MasterDetailDatabase> = MasterDetailDatabase::class.java

    @Provides
    @Singleton
    fun provideDatabaseName(): String = DATABASE_NAME

    @Provides
    @Singleton
    fun provideDatabase(context: Context, databaseClass: Class<MasterDetailDatabase>, databaseName: String):
            MasterDetailDatabase = Room.databaseBuilder(context, databaseClass, databaseName)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideCoinsDao(database: MasterDetailDatabase): CoinsDao = database.coinsDao()
}