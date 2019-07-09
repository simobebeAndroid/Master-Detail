package com.simiomobile.masterdetail.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.simiomobile.masterdetail.MasterDetailApplication
import com.simiomobile.masterdetail.R
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.ui.detail.DetailActivity
import com.simiomobile.masterdetail.ui.detail.EXTRA_COIN_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListCoinsItemListener {

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java).also {
            MasterDetailApplication.component.inject(it)
        }
    }

    private var mAdapter: ListCoinsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListener()
    }

    override fun onStart() {
        super.onStart()
        observeListCoins()
    }

    private fun initListener() {
        listCoinsSwipeRefreshLayout.setOnRefreshListener {
            observeListCoins()
        }
    }

    override fun onItemSelected(item: CoinsData) {
        startActivity(Intent(this@MainActivity, DetailActivity::class.java).putExtra(EXTRA_COIN_ID, item.coinsId))
    }

    override fun onItemFavorite(item: CoinsData) {
        if (item.isFavorite) {
            mViewModel.addFavoriteCoins(item)
        } else {
            mViewModel.deleteFavoriteCoins(item)
        }
    }

    private fun initViews() {
        listCoinsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            mAdapter = ListCoinsAdapter(this@MainActivity)
            adapter = mAdapter
        }
    }

    private fun observeListCoins() {
        listCoinsSwipeRefreshLayout.isRefreshing = true
        mViewModel.getListCoins().observe(this, Observer { listCoins ->
            listCoins?.let {
                if (it.isNotEmpty()) {
                    renderCoins(it)
                } else {
                    // showLoadFailed()
                }
            } ?: run {
                renderCoins(listOf())
                //  showLoadFailed()
            }
            listCoinsSwipeRefreshLayout.isRefreshing = false
        })
    }

    private fun renderCoins(it: List<CoinsData>) {
        mAdapter?.listCoins = it.toMutableList()
    }
}
