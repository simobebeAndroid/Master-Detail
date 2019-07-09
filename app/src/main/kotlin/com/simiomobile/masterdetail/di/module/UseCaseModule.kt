package com.simiomobile.masterdetail.di.module

import com.simiomobile.masterdetail.data.repository.api.CoinsApiRepository
import com.simiomobile.masterdetail.data.repository.local.database.CoinsLocalRepository
import com.simiomobile.masterdetail.data.repository.local.preference.FavoriteCoinsIdLocalRepository
import com.simiomobile.masterdetail.domain.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAllCoinsUseCase(
        coinsApiRepository: CoinsApiRepository,
        coinsLocalRepository: CoinsLocalRepository,
        favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository
    ): GetAllCoinsUseCase =
        GetAllCoinsUseCaseImpl(coinsApiRepository, coinsLocalRepository, favoriteCoinsIdLocalRepository)

    @Singleton
    @Provides
    fun provideGetCoinsByIdUseCasee(
        coinsLocalRepository: CoinsLocalRepository,
        favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository
    ): GetCoinsByIdUseCase =
        GetCoinsByIdUseCaseImpl(coinsLocalRepository, favoriteCoinsIdLocalRepository)

    @Singleton
    @Provides
    fun provideAddFavoriteCoinUseCase(favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository): AddFavoriteCoinUseCase =
        AddFavoriteCoinUseCaseImpl(favoriteCoinsIdLocalRepository)

    @Singleton
    @Provides
    fun provideDeleteFavoriteCoinUseCase(favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository): DeleteFavoriteCoinUseCase =
        DeleteFavoriteCoinUseCaseImpl(favoriteCoinsIdLocalRepository)
}