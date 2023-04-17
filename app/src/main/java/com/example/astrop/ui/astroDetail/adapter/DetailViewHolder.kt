package com.example.astrop.ui.astroDetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrop.databinding.ItemDetailTypeBinding
import com.example.astrop.domain.model.AstroDetail

class DetailViewHolder (view: View) : RecyclerView.ViewHolder(view){
    private val binding = ItemDetailTypeBinding.bind(view)

    fun render(astros: AstroDetail, onClickListener: (AstroDetail) -> Unit) {

        //binding.nameAstro.text = astros.name_astro
        Glide.with(itemView.context).load(astros.image_url).into(binding.imgViewUrl)
        itemView.setOnClickListener {
            onClickListener(astros)

        }

    }

}
