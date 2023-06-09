package com.example.astrop.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrop.R
import com.example.astrop.data.database.entities.AstroDetailEntity
import com.example.astrop.databinding.FragmentHomeBinding
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.ui.home.adapter.HomeAdapter
import com.example.astrop.ui.home.adapter.HomeGridAdapter
import com.example.astrop.utils.FUtils.setBackPressedCallback
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter
    private lateinit var gridAdapter: HomeGridAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setBackPressedCallback {
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavConfig()

        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())

        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapter
        }

        binding.rvPlanets.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.setPadding(5, 0, 5, 0)

            adapter = adapter
        }
        adapter = HomeAdapter { ch -> onItemSelect(ch) }
        gridAdapter = HomeGridAdapter { ch -> onGridItemSelect(ch) }
        binding.rvHome.adapter = adapter
        binding.rvPlanets.adapter = gridAdapter



        setRvs()
        setAnimation()
        greeting()
        goToDailyImage()

    }

    private fun setRvs() {

        viewModel.loading.observe(requireActivity()) { load ->
            binding.loadingContainer.isVisible = load
            binding.homeContainer.isVisible = !load
        }
        viewModel.galaxiesList.observe(requireActivity()) { galaxies ->
            adapter.setList(galaxies)
        }

        viewModel.planetsList.observe(requireActivity()) { planets ->
            gridAdapter.setList(planets)
        }
    }

    private fun goToDailyImage() {
        binding.dailyImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_dailyImageFragment)
        }
    }


    private fun setNavConfig() {

        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        val toolbar = requireActivity().findViewById<AppBarLayout>(R.id.appBarLayout)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        bottomNavigation.visibility = View.VISIBLE
        toolbar.visibility = View.VISIBLE

    }


    private fun greeting() {
        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        val name = prefs.getString(getString(R.string.key_nameU), null)
        binding.hello.text = getString(R.string.hello, name)
    }

    private fun setAnimation() {
        binding.imgBg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.bg_home)
        binding.homeFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)

    }

    private fun onItemSelect(astro: AstroDetail) {
        val nav = HomeFragmentDirections.actionHomeFragment2ToHomeDetailFragment(
            AstroDetailEntity(
                astro.id_astro,
                astro.name_astro,
                astro.type_astro,
                astro.description,
                astro.name_com,
                astro.composition_description,
                astro.distance, astro.image_url, astro.id_type_astro
            )
        )
        findNavController().navigate(nav)
    }

    private fun onGridItemSelect(astro: AstroDetail) {
        val nav = HomeFragmentDirections.actionHomeFragment2ToHomeDetailFragment(
            AstroDetailEntity(
                astro.id_astro,
                astro.name_astro,
                astro.type_astro,
                astro.description,
                astro.name_com,
                astro.composition_description,
                astro.distance, astro.image_url, astro.id_type_astro
            )
        )
        findNavController().navigate(nav)
    }


}