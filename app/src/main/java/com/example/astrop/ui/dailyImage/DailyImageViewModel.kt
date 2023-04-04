package com.example.astrop.ui.dailyImage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    fun getDailyImage(binding: FragmentDailyImageBinding, context: Context)  {
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
                "Error al obtener la imagen diaria: ${e.message}".also { binding.body.text = it }
            }
            finally {
               binding.swipeDaily.isRefreshing = false
            }
        }

    }


}