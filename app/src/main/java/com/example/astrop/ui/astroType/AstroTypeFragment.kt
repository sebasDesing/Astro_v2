package com.example.astrop.ui.astroType

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astrop.R
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.databinding.FragmentAstroTypeBinding
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.model.AstroType
import com.example.astrop.ui.astroType.adapter.AstroTypeAdapter
import com.example.astrop.utils.FUtils.setBackPressedCallback
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
        setBackPressedCallback {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.astroTypesFg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.from_ast)
        binding.swipe.isEnabled = false
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = AstroTypeAdapter(astroList) { ch -> onItemSelect(ch) }
        binding.recyclerView.adapter = adapter
        viewModel.setRecyclerView(astroList, adapter, binding)
        setAnimation()
    }

    private fun setAnimation() {
        val alphaAnimator = ObjectAnimator.ofFloat(binding.textdata, "alpha", 0.2f, 1f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }
        alphaAnimator.start()
    }

    private fun onItemSelect(astro: AstroType) {
        Log.i("HiAstro", "$astro")
        val nav = AstroTypeFragmentDirections.actionAstroTypeFragmentToDetailFragment(
            AstroTypeModel(astro.typeAstro,astro.imgUrl,astro.id_type_astro)
        )
        findNavController().navigate(nav)
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigation = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation.visibility = View.VISIBLE
    }
}