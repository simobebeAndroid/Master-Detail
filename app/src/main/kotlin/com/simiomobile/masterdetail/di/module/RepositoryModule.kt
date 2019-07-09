package com.simiomobile.masterdetail.di.module

import com.simiomobile.masterdetail.api.MasterDetailApi
import com.simiomobile.masterdetail.data.local.database.CoinsDao
import com.simiomobile.masterdetail.data.local.preference.SharedPreference
import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepository
import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepositoryImpl
import com.simiomobile.masterdetail.data.repository.local.database.CoinsLocalRepository
import com.simiomobile.masterdetail.data.repository.local.database.CoinsLocalRepositoryImpl
import com.simiomobile.masterdetail.data.repository.local.preference.FavoriteCoinsIdLocalRepository
import com.simiomobile.masterdetail.data.repository.local.preference.FavoriteCoinsIdLocalRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCoinsApiRepository(remote: MasterDetailApi): CoinsApiRepository =
        CoinsApiRepositoryImpl(remote)

    @Singleton
    @Provides
    fun provideCoinsLocalRepository(coinsDao: CoinsDao): CoinsLocalRepository =
        CoinsLocalRepositoryImpl(coinsDao)

    @Singleton
    @Provides
    fun provideFavoriteCoinsIdLocalRepository(sharedPreference: SharedPreference): FavoriteCoinsIdLocalRepository =
        FavoriteCoinsIdLocalRepositoryImpl(sharedPreference)
}