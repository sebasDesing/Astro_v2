package com.example.astrop.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrop.R
import com.example.astrop.databinding.FragmentHomeBinding
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.ui.home.adapter.HomeAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val astroDetailList = mutableListOf<AstroDetail>()
    private lateinit var adapter: HomeAdapter
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

        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = adapter
        }

        adapter = HomeAdapter(astroDetailList) { ch -> onItemSelect(ch) }
        binding.rvHome.adapter = adapter
        viewModel.setData(astroDetailList, adapter, binding)





        binding.imgBg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.bg_home)
        binding.homeFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)



        binding.astroTypes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_mainFragment)
        }



    }
    private fun onItemSelect(astro: AstroDetail) {
        Log.i("HiAstro", "$astro")
        Toast.makeText(requireContext(), "Hello ${astro.id_astro}", Toast.LENGTH_SHORT).show()
    }


}