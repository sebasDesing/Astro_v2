package com.example.astrop.ui.astroType.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.databinding.ItemTypeAstroBinding

class AstroTypeViewHolder (view: View) : RecyclerView.ViewHolder(view){
private val binding = ItemTypeAstroBinding.bind(view)

    fun render(astros: AstroTypeModel, onClickListener: (AstroTypeModel) -> Unit) {

        binding.nameAstro.text = astros.typeAstro
        Glide.with(itemView.context).load(astros.imageUrl).into(binding.imgViewUrl)

        itemView.setOnClickListener {
            onClickListener(astros)
        }

    }

}
