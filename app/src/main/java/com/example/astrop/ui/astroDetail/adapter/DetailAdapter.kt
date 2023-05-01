package com.example.astrop.ui.astroDetail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astrop.R
import com.example.astrop.domain.model.AstroDetail

class DetailAdapter(
    private val astroList: List<AstroDetail>,
    private val onClickListener: (AstroDetail) -> Unit
) : RecyclerView.Adapter<DetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
     val layoutInflater = LayoutInflater.from(parent.context)
        return  DetailViewHolder(layoutInflater.inflate(R.layout.item_detail_type, parent, false))
    }

    override fun getItemCount(): Int = astroList.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item = astroList[position]
        holder.render(item, onClickListener)
    }


}