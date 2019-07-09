package com.simiomobile.masterdetail.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.simiomobile.masterdetail.R
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.utils.extension.currencyFormat
import com.simiomobile.masterdetail.utils.extension.setOnAnimateClickListener
import kotlinx.android.synthetic.main.item_coins.view.*
import kotlin.properties.Delegates

interface ListCoinsItemListener {
    fun onItemSelected(item: CoinsData)
    fun onItemFavorite(item: CoinsData)
}

class ListCoinsAdapter(private val listener: ListCoinsItemListener) :
    RecyclerView.Adapter<ListCoinsAdapter.ViewHolder>() {

    var listCoins: MutableList<CoinsData>  by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coins,
            parent, false
        )
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.iconFavoriteImageView.setOnAnimateClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                refreshStatusFavorite(viewHolder.adapterPosition)
                listener.onItemFavorite(listCoins[viewHolder.adapterPosition])
            }
        }
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemSelected(listCoins[viewHolder.adapterPosition])
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listCoins.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCoins[position])
    }

    private fun refreshStatusFavorite(position: Int) {
        listCoins[position].isFavorite = !listCoins[position].isFavorite
        notifyItemChanged(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CoinsData) = itemView.apply {
            rankCoinTextView.text = item.marketCapRank.toString()
            Glide.with(iconCoinImageView)
                .load(item.image)
                .into(iconCoinImageView)
            nameCoinTextView.text = item.name
            priceCoinTextView.text = item.currentPrice.currencyFormat()
            val imgFavorite = if (item.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_unfavorite
            }
            iconFavoriteImageView.setImageResource(imgFavorite)

        }
    }
}