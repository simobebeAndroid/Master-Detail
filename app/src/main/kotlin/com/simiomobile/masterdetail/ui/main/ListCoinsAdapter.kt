package com.simiomobile.masterdetail.ui.main

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.simiomobile.masterdetail.R
import com.simiomobile.masterdetail.data.local.model.CoinsData
import com.simiomobile.masterdetail.utils.extension.currencyFormat
import kotlinx.android.synthetic.main.item_coins.view.*
import kotlin.properties.Delegates

interface ListCoinsItemListener {
    fun onItemSelected(item: CoinsData)
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


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CoinsData) = itemView.apply {
            rankCoinTextView.text = item.marketCapRank
            Glide.with(iconCoinImageView)
                .load(item.image)
                .into(iconCoinImageView)
            nameCoinTextView.text = item.name
            priceCoinTextView.apply {
                text = item.currentPrice.currencyFormat()
                val textColor = if (item.currentPrice >= 0) {
                    ContextCompat.getColor(itemView.context, R.color.green)
                } else {
                    ContextCompat.getColor(itemView.context, R.color.red)
                }
                setTextColor(textColor)
            }
            val imgFavorite = if (item.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_unfavorite
            }
            iconFavoriteImageView.setImageResource(imgFavorite)

        }
    }
}