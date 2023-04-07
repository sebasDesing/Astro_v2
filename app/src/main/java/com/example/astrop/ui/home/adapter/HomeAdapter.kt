package com.example.astrop.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astrop.R
import com.example.astrop.domain.model.AstroDetail


class HomeAdapter(
    private val astroDetailList: List<AstroDetail>,
    private val onClickListener: (AstroDetail) -> Unit
) : RecyclerView.Adapter<AstroDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroDetailViewHolder {
        val layoutInflater =LayoutInflater.from((parent.context))

        return AstroDetailViewHolder(layoutInflater.inflate(R.layout.item_detail_list_astro, parent, false))
    }

    override fun getItemCount(): Int =astroDetailList.size

    override fun onBindViewHolder(holder: AstroDetailViewHolder, position: Int) {
        val item = astroDetailList[position]
        holder.render(item, onClickListener)
    }
}