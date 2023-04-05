package com.example.astrop.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.domain.GetAstrosDetailUseCase
import com.example.astrop.domain.model.AstroDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val result : GetAstrosDetailUseCase) : ViewModel() {

    fun  setData(astroDetailList: MutableList<AstroDetail>){
        viewModelScope.launch {
            val response = result.invoke()
            response?.let {res ->
                    astroDetailList.addAll(res)
                Log.i("astrodetaill", "${res.size} ")
            }
        }
    }
}