package com.simiomobile.masterdetail.di.module

import com.simiomobile.masterdetail.api.MasterDetailApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteDataModule(val baseUrl: String) : BaseRemoteData() {

    @Singleton
    @Provides
    fun provideDmpBackEndApi(retrofit: Retrofit): MasterDetailApi =
        retrofit.create(MasterDetailApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofitDmpBackEnd(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()
}