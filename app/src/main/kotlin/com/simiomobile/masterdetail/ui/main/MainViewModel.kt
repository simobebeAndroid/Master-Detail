package com.simiomobile.masterdetail.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.domain.AddFavoriteCoinUseCase
import com.simiomobile.masterdetail.domain.DeleteFavoriteCoinUseCase
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
    @Inject
    lateinit var addFavoriteCoinUseCase: AddFavoriteCoinUseCase
    @Inject
    lateinit var deleteFavoriteCoinUseCase: DeleteFavoriteCoinUseCase

    private val coinsLiveData = SingleLiveEvent<List<CoinsData>>()
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun getAllCoins() {
        getAllCoinsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    coinsLiveData.value = response
                },
                {
                    coinsLiveData.value = null
                }
            ).addTo(disposables)

    }

    fun getListCoins(): LiveData<List<CoinsData>> {
        getAllCoins()
        return coinsLiveData
    }

    fun addFavoriteCoins(coinsData: CoinsData) {
        addFavoriteCoinUseCase.execute(coinsData)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({}, {}).addTo(disposables)
    }

    fun deleteFavoriteCoins(coinsData: CoinsData) {
        deleteFavoriteCoinUseCase.execute(coinsData)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({}, {}).addTo(disposables)
    }
}