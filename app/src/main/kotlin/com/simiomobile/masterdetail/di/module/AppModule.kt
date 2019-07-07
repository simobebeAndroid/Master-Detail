package com.simiomobile.masterdetail.di.module

import android.content.Context
import com.simiomobile.masterdetail.MasterDetailApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: MasterDetailApplication) {
    @Singleton
    @Provides
    fun provideContext(): Context = app.applicationContext
}