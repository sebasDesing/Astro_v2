package com.example.astrop.ui.astroDetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.databinding.FragmentDetailBinding
import com.example.astrop.domain.GetAstroTypeUseCase
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.model.AstroType
import com.example.astrop.domain.model.GetDetailByTypeUseCase
import com.example.astrop.ui.astroDetail.adapter.DetailAdapter
import com.example.astrop.ui.astroType.adapter.AstroTypeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val result: GetDetailByTypeUseCase) : ViewModel() {

    fun hola(id: Int) {
    viewModelScope.launch{
        val response = result.invoke(id)

        Log.i("holaaaaa","${response.size}")
    }
    }


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