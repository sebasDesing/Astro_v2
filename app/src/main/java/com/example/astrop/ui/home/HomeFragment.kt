package com.example.astrop.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val args: HomeFragmentArgs by navArgs()
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
        binding.imgBg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.bg_home)
        binding.homeFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)
        binding.homeOptions.animation =AnimationUtils.loadAnimation(requireContext(),R.anim.from_home)
        Glide.with(requireContext())
            .load(args.photoUrl)
            .into(binding.userImg)


        binding.textAstros.text= args.nameUser

        // Sesion

        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        ).edit()
        prefs.putString("email", args.email)
        prefs.putString("nameU", args.nameUser)
        prefs.putString("imgU", args.photoUrl)
            .apply()
        binding.sigout.setOnClickListener {
            prefs.clear()
            prefs.apply()
            findNavController().navigate(R.id.sigInFragment)
        }
        binding.astroTypes.setOnClickListener {
            Toast.makeText(requireContext(), "Astro types",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.astroTypeFragment)
        }

        binding.dailyImg.setOnClickListener {
            Toast.makeText(requireContext(), "Daily image",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment2_to_dailyImageFragment)
        }

    }


}