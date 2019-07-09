package com.simiomobile.masterdetail.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.simiomobile.masterdetail.MasterDetailApplication
import com.simiomobile.masterdetail.R
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.utils.extension.changeDateFormat
import com.simiomobile.masterdetail.utils.extension.currencyFormat
import com.simiomobile.masterdetail.utils.extension.setOnAnimateClickListener
import kotlinx.android.synthetic.main.activity_detail.*

const val EXTRA_COIN_ID = "extraCoinId"

class DetailActivity : AppCompatActivity() {

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java).also {
            MasterDetailApplication.component.inject(it)
        }
    }
    private var mAdapter: CoinsDetailAdapter? = null
    private var mCoinId = ""
    private var mCoinData: CoinsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
        initListener()
        intent.extras?.let {
            mCoinId = it.getString(EXTRA_COIN_ID) ?: ""
            observeCoinDetail()
        }
    }

    private fun initViews() {
        detailCoinRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            setHasFixedSize(true)
            mAdapter = CoinsDetailAdapter()
            adapter = mAdapter
        }
    }

    private fun initListener() {
        iconFavoriteImageView.setOnAnimateClickListener {
            if (mCoinData?.isFavorite == true) {
                unFavoriteCoin()
            } else {
                addFavoriteCoin()
            }
            mCoinData?.isFavorite = !(mCoinData?.isFavorite ?: false)
            setIconFavorite()
        }
    }

    private fun bindView(){
        setIconFavorite()
        mCoinData?.image?.let {
            Glide.with(imageCoinImageView)
                .load(it)
                .into(imageCoinImageView)
        }
    }

    private fun observeCoinDetail() {
        mViewModel.getCoins(mCoinId).observe(this, Observer {
            mCoinData = it
            renderCoinDetail(mCoinData)
            bindView()
        })
    }

    private fun addFavoriteCoin() {
        mCoinData?.let {
            mViewModel.addFavoriteCoins(it)
        }
    }

    private fun unFavoriteCoin() {
        mCoinData?.let {
            mViewModel.deleteFavoriteCoins(it)
        }
    }

    private fun setIconFavorite() {
        val imgFavorite = if (mCoinData?.isFavorite == true) {
            R.drawable.ic_favorite
        } else {
            R.drawable.ic_unfavorite
        }
        iconFavoriteImageView.setImageResource(imgFavorite)
    }
    private fun renderCoinDetail(coinsData : CoinsData?) {
        coinsData?.let{
            val listDetail = mutableListOf<Pair<String, String>>()
            listDetail.add(Pair("Price",it.currentPrice.currencyFormat()))
            listDetail.add(Pair("Market Cap",it.marketCap.currencyFormat()))
            listDetail.add(Pair("High 24h",it.high24h))
            listDetail.add(Pair("Low 24h",it.low24h))
            if(it.roiCurrency.isNotEmpty())listDetail.add(Pair("ROI Currency", it.roiCurrency))
            if(it.roiPercentage.isNotEmpty())listDetail.add(Pair("ROI Percentage", it.roiPercentage))
            if(it.roiTime.isNotEmpty())listDetail.add(Pair("ROI Times", it.roiTime))
            listDetail.add(Pair("Last Update",it.lastUpdated.changeDateFormat()))
            mAdapter?.listCoinsDetail = listDetail
        }
    }
}
