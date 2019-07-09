package com.simiomobile.masterdetail.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simiomobile.masterdetail.R
import kotlinx.android.synthetic.main.item_coins_detail.view.*
import kotlin.properties.Delegates

class CoinsDetailAdapter() :
    RecyclerView.Adapter<CoinsDetailAdapter.ViewHolder>() {

    var listCoinsDetail: MutableList<Pair<String, String>>  by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coins_detail,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCoinsDetail.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCoinsDetail[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pair<String, String>) = itemView.apply {
            val (name, value) = item
            nameTextView.text = name
            valueTextView.text = value
        }
    }
}