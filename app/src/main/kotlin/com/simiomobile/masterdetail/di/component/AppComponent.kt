package com.simiomobile.masterdetail.di.component

import com.simiomobile.masterdetail.di.module.*
import com.simiomobile.masterdetail.ui.detail.DetailViewModel
import com.simiomobile.masterdetail.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        RemoteDataModule::class,
        DataModule::class,
        RepositoryModule::class,
        UseCaseModule::class]
)
interface AppComponent {
    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: DetailViewModel)
}