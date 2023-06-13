package com.example.astrop.ui.astroDetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.databinding.FragmentDetailBinding
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.use_case.GetDetailByTypeUseCase
import com.example.astrop.ui.astroDetail.adapter.DetailAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val result: GetDetailByTypeUseCase) : ViewModel() {
    /* IMPLEMENTA EL CASO DE USO QUE TRAE LOS DETALLES DE CADA ASTRO, MEDIATE SU ID */

    @SuppressLint("NotifyDataSetChanged")
    fun setRecyclerView(
        astroList: MutableList<AstroDetail>,
        adapter: DetailAdapter,
        binding: FragmentDetailBinding, id:Int
    ) {
        viewModelScope.launch {

            try {
                val response = result.invoke(id)
                response.let { res ->
                    if (astroList.size==0){
                        astroList.addAll(res)
                        adapter.notifyDataSetChanged()
                    }

                }
            }catch (e :Exception){
                "Error al obtener los datos : ${e.message}".also { binding.text.text = it }
            }



        }
    }
}