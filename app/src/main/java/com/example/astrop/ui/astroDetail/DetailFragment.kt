package com.example.astrop.ui.astroDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.FragmentDetailBinding
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.ui.astroDetail.adapter.DetailAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private  val viewModel : DetailViewModel by viewModels()
    private val astroList = mutableListOf<AstroDetail>()
    private lateinit var adapter: DetailAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation.visibility = View.GONE

        binding.rvDetail.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = adapter
            this.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        adapter = DetailAdapter(astroList) { ch -> onItemSelect(ch) }
        binding.rvDetail.adapter = adapter
        viewModel.setRecyclerView(astroList, adapter, binding, args.astroType.idType)
        val helptext = if (args.astroType.idType == 5|| args.astroType.idType == 2) {
            getString(R.string.helptext_galaxi, args.astroType.typeAstro)
        } else {
            getString(R.string.helptext_astro, args.astroType.typeAstro)
        }
        binding.text.text = helptext


    }

    private fun onItemSelect(astro: AstroDetail) {

        binding.textNameAstro.text = astro.name_astro
        binding.textTypeAstro.text = astro.type_astro
        binding.textCompositionNameAstro.text = astro.name_com
        binding.textDescription.text= astro.description
        Glide.with(requireContext()).load(astro.image_url).into(binding.imageViewB)

    }


}