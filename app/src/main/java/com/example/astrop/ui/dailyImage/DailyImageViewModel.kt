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
                        //binding.dateImage.text = "Date : ${data.date}"
                        binding.textDescription.text = data.explanation
                        binding.date.text = data.date
                        binding.titlePic.text = data.title
                        Glide.with(context).load(data.hdurl).into(binding.imageViewB)
                    }
                }
            } catch (e: Exception) {
                "Error al obtener la imagen diaria: ${e.message}".also { binding.textDescription.text = it }
            }
            finally {
               binding.swipeDaily.isRefreshing = false
            }
        }

    }


}