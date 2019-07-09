package com.simiomobile.masterdetail.domain
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.data.repository.local.preference.FavoriteCoinsIdLocalRepository
import io.reactivex.Completable

interface AddFavoriteCoinUseCase {
    fun execute(coinsData: CoinsData): Completable
}

class AddFavoriteCoinUseCaseImpl(private val favoriteCoinsIdLocalRepository: FavoriteCoinsIdLocalRepository) :
    AddFavoriteCoinUseCase {
    override fun execute(coinsData: CoinsData): Completable {
        return if (coinsData.coinsId.isNotEmpty()) {
            favoriteCoinsIdLocalRepository.getFavoriteCoinsIds()
                .flatMapCompletable { favoriteIds ->
                    if (!favoriteIds.contains(coinsData.coinsId)) {
                        val ids = favoriteIds.toMutableList()
                        ids.add(coinsData.coinsId)
                        favoriteCoinsIdLocalRepository.setFavoriteCoinsIds(ids)
                    } else {
                        Completable.complete()
                    }
                }
        } else {
            Completable.complete()
        }
    }

}