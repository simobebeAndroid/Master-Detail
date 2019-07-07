package com.simiomobile.masterdetail.di.module

import com.simiomobile.masterdetail.api.MasterDetailApi
import com.simiomobile.masterdetail.data.local.CoinsDao
import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepository
import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepositoryImpl
import com.simiomobile.masterdetail.data.repository.local.CoinsLocalRepository
import com.simiomobile.masterdetail.data.repository.local.CoinsLocalRepositoryImpl
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
}