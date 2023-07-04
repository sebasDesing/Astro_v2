package com.example.astrop.ui.astroType

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astrop.domain.model.AstroType
import com.example.astrop.domain.use_case.get_astro_type.GetAstroTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AstroTypeViewModel @Inject constructor(private val result: GetAstroTypeUseCase) :
    ViewModel() {
    /* IMPLEMENTA EL CASO DE USO QUE TRAE LOS TIPOS DE ASTROS*/
    private val _lisAstroType: MutableLiveData<List<AstroType>> = MutableLiveData()
    val listAstroType: LiveData<List<AstroType>> get() = _lisAstroType
    private var _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    init {
        getAstroTypes()
    }

    private fun getAstroTypes() {
        viewModelScope.launch {
            val response = result.invoke()
            _lisAstroType.value = response
            _loading.value = false
        }
    }


}

