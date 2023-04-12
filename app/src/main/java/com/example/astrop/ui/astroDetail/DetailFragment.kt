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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrop.databinding.FragmentDetailBinding
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.ui.astroDetail.adapter.DetailAdapter
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

        binding.text.text = args.toString()

        binding.rvDetail.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = adapter
        }
        adapter = DetailAdapter(astroList) { ch -> onItemSelect(ch) }
       binding.rvDetail.adapter = adapter
        viewModel.setRecyclerView(astroList, adapter, binding, args.idType)
        viewModel.hola(args.idType)
    }

    private fun onItemSelect(astro: AstroDetail) {

        Toast.makeText(requireContext(), "Hello ${astro.id_astro}", Toast.LENGTH_SHORT).show()


    }


}