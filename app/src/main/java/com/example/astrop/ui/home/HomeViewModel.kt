package com.example.astrop.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.databinding.FragmentDailyImageBinding
import com.example.astrop.databinding.FragmentHomeBinding
import com.example.astrop.domain.GetAstrosDetailUseCase
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.ui.home.adapter.HomeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val result : GetAstrosDetailUseCase) : ViewModel() {

    @SuppressLint("NotifyDataSetChanged")
    fun  setData(astroDetailList: MutableList<AstroDetail>, adapter: HomeAdapter, binding: FragmentHomeBinding ){
        viewModelScope.launch {
            val response = result.invoke()
            response?.let {res ->
                    astroDetailList.addAll(res)
                adapter.notifyDataSetChanged()
                Log.i("astrodetaill", "${res.size} ")

            }
        }
    }
}