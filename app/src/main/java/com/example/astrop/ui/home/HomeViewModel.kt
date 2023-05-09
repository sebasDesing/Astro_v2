package com.example.astrop.ui.home

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _galaxiesDetailList: MutableLiveData<List<AstroDetail>> = MutableLiveData()
    private val _planetsDetailList: MutableLiveData<List<AstroDetail>> = MutableLiveData()
    val galaxiesList: LiveData<List<AstroDetail>> get() = _galaxiesDetailList
    val planetsList: LiveData<List<AstroDetail>> get() = _planetsDetailList

    init {
        getGalaxies()
        getPlanets()
    }

    private fun getPlanets() {
        viewModelScope.launch {
            result.invoke()
            val response = fromData.invoke(PLANETS_ID)
            _planetsDetailList.value = response
            Log.i("planetass", "$response")
        }
    }

    private fun getGalaxies() {
        viewModelScope.launch {
            result.invoke()
            val response = fromData.invoke(GALAXY_ID)
            _galaxiesDetailList.value = response
            Log.i("galaxias", "$response")
        }
    }


}