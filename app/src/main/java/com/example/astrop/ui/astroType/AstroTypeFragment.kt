package com.example.astrop.ui.astroType

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.ui.astroType.adapter.AstroTypeAdapter


class AstroTypeFragment : Fragment() {

    private var _binding: FragmentAstroTypeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AstroTypeViewModel by viewModels()
    private val astroList = mutableListOf<AstroTypeModel>()
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

    private fun onItemSelect(astro: AstroTypeModel) {
        Log.i("HiAstro", "$astro")
    }
}