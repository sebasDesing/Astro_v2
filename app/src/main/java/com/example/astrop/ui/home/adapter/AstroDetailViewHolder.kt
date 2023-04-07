package com.example.astrop.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrop.databinding.ItemDetailListAstroBinding
import com.example.astrop.domain.model.AstroDetail

class AstroDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDetailListAstroBinding.bind(view)

    fun render(astrosDetail: AstroDetail, onClickListener: (AstroDetail) -> Unit) {
        binding.nameAstro.text = astrosDetail.name_astro
        Glide.with(itemView.context).load(astrosDetail.image_url).into(binding.imgViewUrl)

        itemView.setOnClickListener {
            onClickListener(astrosDetail)
        }

    }
}
