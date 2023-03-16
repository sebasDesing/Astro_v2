package com.example.astrop.ui.astroType.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.databinding.ItemTypeAstroBinding

class AstroTypeViewHolder (view: View) : RecyclerView.ViewHolder(view){
private val binding = ItemTypeAstroBinding.bind(view)

    fun render(astros: AstroTypeModel, onClickListener: (AstroTypeModel) -> Unit) {

        binding.nameAstro.text = astros.typeAstro
        binding.imgAstro.text = astros.imageUrl

    }

}
