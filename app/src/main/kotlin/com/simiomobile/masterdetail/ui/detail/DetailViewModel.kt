package com.simiomobile.masterdetail.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.domain.AddFavoriteCoinUseCase
import com.simiomobile.masterdetail.domain.DeleteFavoriteCoinUseCase
import com.simiomobile.masterdetail.domain.GetCoinsByIdUseCase
import com.simiomobile.masterdetail.utils.SingleLiveEvent
import com.simiomobile.masterdetail.utils.extension.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel : ViewModel() {
    @Inject
    lateinit var getCoinsByIdUseCase: GetCoinsByIdUseCase
    @Inject
    lateinit var addFavoriteCoinUseCase: AddFavoriteCoinUseCase
    @Inject
    lateinit var deleteFavoriteCoinUseCase: DeleteFavoriteCoinUseCase

    private val coinLiveData = SingleLiveEvent<CoinsData>()
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun getCoinDetail(coinId: String) {
        getCoinsByIdUseCase.execute(coinId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    coinLiveData.value = response
                },
                {
                    coinLiveData.value = null
                }
            ).addTo(disposables)

    }

    fun getCoins(coinId: String): LiveData<CoinsData> {
        getCoinDetail(coinId)
        return coinLiveData
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