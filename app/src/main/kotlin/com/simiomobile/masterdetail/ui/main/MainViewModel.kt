package com.simiomobile.masterdetail.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.simiomobile.masterdetail.api.response.allcoins.GetAllCoinsWithMarketResponse
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.domain.GetAllCoinsUseCase
import com.simiomobile.masterdetail.utils.SingleLiveEvent
import com.simiomobile.masterdetail.utils.extension.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var getAllCoinsUseCase: GetAllCoinsUseCase

    private val coinsLiveData = SingleLiveEvent<List<CoinsData>>()
    private val isLoading = MutableLiveData<Boolean>()
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun getAllCoins() {
        isLoading.value = true
        getAllCoinsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    isLoading.value = false
                    coinsLiveData.value = response
                },
                {
                    isLoading.value = false
                    coinsLiveData.value = null
                }
            ).addTo(disposables)

    }

    fun isLoading() = isLoading

    fun getListCoins(): LiveData<List<CoinsData>> {
        getAllCoins()
        return coinsLiveData
    }
}