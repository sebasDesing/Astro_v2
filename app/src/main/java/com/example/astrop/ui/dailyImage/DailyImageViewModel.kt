package com.example.astrop.ui.dailyImage

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.astrop.databinding.FragmentDailyImageBinding
import com.example.astrop.domain.use_case.get_daily_image.GetDailyImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyImageViewModel @Inject constructor(private val result: GetDailyImageUseCase) :
    ViewModel() {


    @SuppressLint("SetTextI18n")
    fun getDailyImage(binding: FragmentDailyImageBinding, context: Context)  {
        viewModelScope.launch {
            try {
                binding.swipeDaily.isRefreshing = true
                val response = result.invoke()
                if (!response.isNullOrEmpty()) {
                    response.let { res ->
                        val data = res[0]

                        binding.textDescription.text = data.explanation
                        binding.date.text = data.date
                        binding.titleArticle.text = data.title
                        Glide.with(context).load(data.hdurl).into(binding.imageViewB)
                    }
                }
            } catch (e: Exception) {
                "Error al obtener la imagen diaria: ${e.message}".also { binding.textDescription.text = it }
            }
            finally {
                binding.swipeDaily.isRefreshing = false
                binding.dailyImageLoading.isVisible =false
               binding.dailyImageContainer.isVisible = true
            }
        }

    }


}