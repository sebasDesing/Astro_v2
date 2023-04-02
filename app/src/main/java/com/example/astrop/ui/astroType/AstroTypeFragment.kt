package com.example.astrop.ui.astroType

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrop.R
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.domain.model.AstroType
import com.example.astrop.ui.astroType.adapter.AstroTypeAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AstroTypeFragment : Fragment() {

    private var _binding: FragmentAstroTypeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AstroTypeViewModel by viewModels()
    private val astroList = mutableListOf<AstroType>()
    private lateinit var adapter: AstroTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAstroTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation.visibility = View.GONE
        binding.astroTypesFg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.from_ast)
        binding.swipe.isEnabled = false
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(), LinearLayoutManager.VERTICAL
            )
        )
        adapter = AstroTypeAdapter(astroList) { ch -> onItemSelect(ch) }
        binding.recyclerView.adapter = adapter
        viewModel.setRecyclerView(astroList, adapter, binding)
    }

    private fun onItemSelect(astro: AstroType) {
        Log.i("HiAstro", "$astro")
        Toast.makeText(requireContext(), "Hello ${astro.typeAstro}",Toast.LENGTH_SHORT).show()
    }
}