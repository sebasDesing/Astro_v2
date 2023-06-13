package com.example.astrop.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.domain.use_case.get_astro_detail.GetAstroDetailUseCase
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.use_case.GetDetailByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val result: GetAstroDetailUseCase,
    private val fromData: GetDetailByTypeUseCase
) : ViewModel() {
    private val GALAXY_ID = 2
    private val PLANETS_ID = 1

    private val _galaxiesDetailList: MutableLiveData<List<AstroDetail>> = MutableLiveData()
    private val _planetsDetailList: MutableLiveData<List<AstroDetail>> = MutableLiveData()
    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val galaxiesList: LiveData<List<AstroDetail>> get() = _galaxiesDetailList
    val planetsList: LiveData<List<AstroDetail>> get() = _planetsDetailList
    val loading: LiveData<Boolean> get() = _loading

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
            _loading.value = false
        }
    }

    private fun getGalaxies() {
        viewModelScope.launch {
            result.invoke()
            val response = fromData.invoke(GALAXY_ID)
            _galaxiesDetailList.value = response
            Log.i("galaxias", "$response")
            _loading.value = false
        }
    }


}