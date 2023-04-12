package com.example.astrop.ui.astroType

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.domain.GetAstroTypeUseCase
import com.example.astrop.domain.model.AstroType
import com.example.astrop.ui.astroType.adapter.AstroTypeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstroTypeViewModel @Inject constructor(private val result: GetAstroTypeUseCase) :
    ViewModel() {

    @SuppressLint("NotifyDataSetChanged")
    fun setRecyclerView(
        astroList: MutableList<AstroType>,
        adapter: AstroTypeAdapter,
        binding: FragmentAstroTypeBinding
    ) {
        viewModelScope.launch {
            binding.swipe.isRefreshing = true
            try {
                val response = result.invoke()
                response.let { res ->
                    if (astroList.size==0){
                        astroList.addAll(res)
                        adapter.notifyDataSetChanged()
                    }

                }
            }catch (e :Exception){
                "Error al obtener los datos : ${e.message}".also { binding.textdata.text = it }
            }

            binding.swipe.isRefreshing = false

        }
    }


}

