package com.simiomobile.masterdetail

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.bumptech.glide.Glide
import com.simiomobile.masterdetail.api.BASE_URL
import com.simiomobile.masterdetail.di.component.AppComponent
import com.simiomobile.masterdetail.di.component.DaggerAppComponent
import com.simiomobile.masterdetail.di.module.*

class MasterDetailApplication :Application(){
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }


    private fun buildDependencyGraph() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .remoteDataModule(RemoteDataModule(BASE_URL))
            .dataModule(DataModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
    }
}

