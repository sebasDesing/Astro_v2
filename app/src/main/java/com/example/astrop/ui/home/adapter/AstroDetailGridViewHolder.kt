package com.example.astrop.ui.home.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.ItemTypeAstroGridBinding
import com.example.astrop.domain.model.AstroDetail

class AstroDetailGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val bindingGrid = ItemTypeAstroGridBinding.bind(view)
    fun renderGrid(astrosDetail: AstroDetail, onClickListener: (AstroDetail) -> Unit) {
        bindingGrid.textGrid.text = astrosDetail.name_astro
        Glide.with(itemView.context).load(astrosDetail.image_url).into(bindingGrid.typeImgGrid)
        bindingGrid.typeImgGrid.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.from_home_items)
        itemView.setOnClickListener {
            onClickListener(astrosDetail)
        }
    }

}
