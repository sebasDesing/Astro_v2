package com.example.astrop.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        val toolbar = requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        bottomNavigation.visibility = View.VISIBLE
        toolbar.visibility = View.VISIBLE

        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )

        val nameUser = prefs.getString("nameU", null)


        binding.imgBg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.bg_home)
        binding.homeFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)
        binding.homeOptions.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_home)




        binding.astroTypes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_mainFragment)
        }



    }



}