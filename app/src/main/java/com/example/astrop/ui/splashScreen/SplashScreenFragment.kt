package com.example.astrop.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.astrop.R
import com.example.astrop.databinding.FragmentSplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({

            if(onboardingEnd()){
                findNavController().navigate(R.id.sigInFragment)
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_onboardingFragment)
            }

        }, 3000)

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgSplash.setImageResource(R.drawable.ic_launcher_f)
        binding.textSplash.text = getString(R.string.app_name)
        binding.imgSplash.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_top)
        binding.textSplash.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)
    }

    private fun onboardingEnd():Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return  sharedPreferences.getBoolean("finished", false)
    }


}