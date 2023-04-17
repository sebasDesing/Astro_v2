package com.example.astrop.ui.homeDetail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.data.database.entities.AstroDetailEntity
import com.example.astrop.data.model.AstroDetailModel
import com.example.astrop.databinding.FragmentHomeDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeDetailFragment : Fragment() {
    private var _binding: FragmentHomeDetailBinding? = null
    private val binding get() = _binding!!
    private val args: HomeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
        val data = args.data
        setDetail(data)
    }

    private fun setDetail(data: AstroDetailEntity) {
        Glide.with(requireContext()).load(data.image_url).into(binding.imageViewB)
        binding.textNameAstro.text = getString(R.string.name_astro, data.name_astro)
        binding.textTypeAstro.text = getString(R.string.type_astro,data.type_astro)
        binding.titleComposition.text = getString(R.string.title_composition, data.name_com)

        binding.textDescription.text = getString(R.string.home_detail)
        val alphaAnimator = ObjectAnimator.ofFloat(binding.textDescription, "alpha", 0.2f, 1f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }
        alphaAnimator.start()

        binding.textDescription.setOnClickListener {
            val nav = HomeDetailFragmentDirections.actionHomeDetailFragmentToAstroTypeFragment()
            findNavController().navigate(nav)
        }
    }


}