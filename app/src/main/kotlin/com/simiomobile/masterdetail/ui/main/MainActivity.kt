package com.simiomobile.masterdetail.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.simiomobile.masterdetail.MasterDetailApplication
import com.simiomobile.masterdetail.R
import com.simiomobile.masterdetail.data.local.model.CoinsData
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
        observeListCoins()
    }

    override fun onItemSelected(item: CoinsData) {

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
        })
    }

    private fun renderCoins(it: List<CoinsData>) {
        mAdapter?.listCoins = it.toMutableList()
    }
}
