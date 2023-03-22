package com.example.astrop.ui.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.astrop.R
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.end.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment2)
            onboardingEnd()
        }
    }
private fun onboardingEnd(){
    val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("finished", true)
    editor.apply()
}
    
}