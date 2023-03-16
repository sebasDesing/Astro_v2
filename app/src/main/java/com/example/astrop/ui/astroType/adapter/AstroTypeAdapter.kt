package com.example.astrop.ui.astroType.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astrop.R
import com.example.astrop.data.model.AstroTypeModel

class AstroTypeAdapter(
    private val astroList: List<AstroTypeModel>,
    private val onClickListener: (AstroTypeModel) -> Unit
) : RecyclerView.Adapter<AstroTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AstroTypeViewHolder(layoutInflater.inflate(R.layout.item_type_astro, parent, false))
    }

    override fun getItemCount(): Int = astroList.size

    override fun onBindViewHolder(holder: AstroTypeViewHolder, position: Int) {
        val item = astroList[position]
        holder.render(item, onClickListener)
    }
}