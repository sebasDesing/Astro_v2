package com.example.astrop.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.databinding.FragmentHomeBinding
import com.example.astrop.domain.GetAstrosDetailUseCase
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.model.GetDetailByTypeUseCase
import com.example.astrop.ui.home.adapter.HomeAdapter
import com.example.astrop.ui.home.adapter.HomeGridAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val result: GetAstrosDetailUseCase,
    private val fromData: GetDetailByTypeUseCase
) : ViewModel() {
    private val GALAXY_ID = 2
    private val PLANETS_ID = 1


    /*HACE LA PETICION A LA API Y LLRNA EL RV HORIZONTAL*/
    fun setData(
        astroDetailList: MutableList<AstroDetail>,
        adapter: HomeAdapter,
        binding: FragmentHomeBinding
    ) {
        viewModelScope.launch {
            try {
                result.invoke()
                val response = fromData.invoke(GALAXY_ID)
                response.let { res ->
                    if (astroDetailList.size == 0) {
                        astroDetailList.addAll(res)
                        adapter.notifyDataSetChanged()
                        Log.i("astrodetaill", "${res} ")
                    }
                }
            } catch (e: Exception) {

                "Error al obtener los datos : ${e.message}".also { binding.hello.text = it }
            }
            finally {
                binding.loadingContainer.isVisible = false
                binding.homeContainer.isVisible = true
            }
        }
    }

    /*HACE LA PETICION A LA API Y LLRNA EL RV A MANERA DE UN GRID  */
    fun setGridRv(astroGridDetailList: MutableList<AstroDetail>, adapter: HomeGridAdapter, binding: FragmentHomeBinding ) {
        viewModelScope.launch {
            try {
                result.invoke()
                val response = fromData.invoke(PLANETS_ID)
                response.let { res ->
                    if (astroGridDetailList.size == 0) {
                        astroGridDetailList.addAll(res)
                        adapter.notifyDataSetChanged()
                        Log.i("astrodetaill", "${res} ")
                    }
                }
            } catch (e: Exception) {

                "Error al obtener los datos : ${e.message}".also { binding.hello.text = it }
            } finally {

            }
        }
    }



}