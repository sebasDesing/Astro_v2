package com.example.astrop.ui.dailyImage

import android.content.Context
import android.view.View
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
            binding.swipeDaily.isRefreshing =true
            try {
                val response = result.invoke()
                if (!response.isNullOrEmpty()) {
                    response.let { res ->
                        val data = res[0]
                        binding.dateImage.text = "date ${data.date}"
                        binding.body.text = data.explanation
                        Glide.with(context).load(data.hdurl).into(binding.dailyImage)
                    }
                }
            } catch (e: Exception) {
                binding.body.text = "Error al obtener la imagen diaria: ${e.message}"
            }
            finally {
               binding.swipeDaily.isRefreshing = false
            }
        }

    }

    fun onReload(binding: FragmentDailyImageBinding, context: Context){
        binding.swipeDaily.setOnRefreshListener{
            getDailyImage(binding, context)
            binding.swipeDaily.isRefreshing= false
        }
    }

}