package com.simiomobile.masterdetail.di.module

import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepository
import com.simiomobile.masterdetail.data.repository.local.CoinsLocalRepository
import com.simiomobile.masterdetail.domain.GetAllCoinsUseCase
import com.simiomobile.masterdetail.domain.GetAllCoinsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAllCoinsUseCase(
        coinsApiRepository: CoinsApiRepository,
        coinsLocalRepository: CoinsLocalRepository
    ): GetAllCoinsUseCase =
        GetAllCoinsUseCaseImpl(coinsApiRepository, coinsLocalRepository)
}