package com.example.astrop.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

object FUtils {
    /* DESHABILITA LA FLECHA DE RETROCESO     */
    fun Fragment.setBackPressedCallback(callback: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callback()
            }
        })
    }
}