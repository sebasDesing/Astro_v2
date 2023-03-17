package com.example.astrop.ui.astroType

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.domain.GetAstroTypeUseCase
import com.example.astrop.ui.astroType.adapter.AstroTypeAdapter
import kotlinx.coroutines.launch

class AstroTypeViewModel: ViewModel() {
    private val result = GetAstroTypeUseCase()

    @SuppressLint("NotifyDataSetChanged")
    fun setRecyclerView(
        astroList: MutableList<AstroTypeModel>,
        adapter: AstroTypeAdapter,
        binding: FragmentAstroTypeBinding
    ) {
        viewModelScope.launch {
            binding.swipe.isRefreshing = true
            val response = result.invoke()
            response?.let { res ->
                astroList.addAll(res)
                adapter.notifyDataSetChanged()
            }
            binding.swipe.isRefreshing = false

        }
    }


}