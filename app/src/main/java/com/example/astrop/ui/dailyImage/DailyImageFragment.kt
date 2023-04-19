package com.example.astrop.ui.dailyImage

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.astrop.R
import com.example.astrop.databinding.FragmentDailyImageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyImageFragment : Fragment() {
    private var _binding: FragmentDailyImageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DailyImageViewModel by viewModels()
    private val MAX_LINES = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDailyImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation.visibility = View.GONE
        binding.dailyImageFg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.from_ast)
        viewModel.getDailyImage(binding, requireContext())

        binding.seemore.setOnClickListener {
            binding.textDescription.maxLines = MAX_LINES
            binding.seemore.visibility = View.GONE
            binding.swipeDaily.isEnabled =false
        }
        binding.swipeDaily.setOnRefreshListener {
            viewModel.getDailyImage(binding, requireContext())
        }
    }
}