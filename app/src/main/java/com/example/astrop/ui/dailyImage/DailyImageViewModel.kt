package com.example.astrop.ui.dailyImage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.astrop.databinding.FragmentDailyImageBinding
import com.example.astrop.domain.GetDailyImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyImageViewModel @Inject constructor(private val result: GetDailyImageUseCase) :
    ViewModel() {

    fun getDailyImage( binding: FragmentDailyImageBinding, context: Context)  {

        viewModelScope.launch {
            val response = result.invoke()
            response?.let { res ->

                Log.i("Dailyyyy", "${res}")


            }

        }

    }

}