package com.example.astrop.ui.astroType.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.ItemTypeAstroBinding
import com.example.astrop.domain.model.AstroType

class AstroTypeViewHolder (view: View) : RecyclerView.ViewHolder(view){
private val binding = ItemTypeAstroBinding.bind(view)

    fun render(astros: AstroType, onClickListener: (AstroType) -> Unit) {

        binding.nameAstro.text = astros.typeAstro
        Glide.with(itemView.context).load(astros.imgUrl).into(binding.typeImg)
        binding.astroTypeItem.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.from_astro_type_items)
        itemView.setOnClickListener {
            onClickListener(astros)
        }

    }

}
