package com.example.astrop.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astrop.R
import com.example.astrop.domain.model.AstroDetail

class HomeGridAdapter(
    private val astroDetailList: List<AstroDetail>,
    private val onClickListener: (AstroDetail) -> Unit
) : RecyclerView.Adapter<AstroDetailGridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroDetailGridViewHolder {
        val layoutInflater = LayoutInflater.from((parent.context))

        return AstroDetailGridViewHolder(layoutInflater.inflate(R.layout.item_type_astro_grid, parent, false))
    }


    override fun getItemCount(): Int =astroDetailList.size

    override fun onBindViewHolder(holder: AstroDetailGridViewHolder, position: Int) {
        val item = astroDetailList[position]
        holder.renderGrid(item, onClickListener)
    }
}