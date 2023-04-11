package com.example.astrop.ui.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.FragmentUserProfileBinding
import com.example.astrop.utils.FUtils.setBackPressedCallback

class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        setBackPressedCallback {
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setAnimation()
        getCurretUSer()
    }


    private fun setAnimation() {
        binding.imgBg.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.bg_home)
        binding.homeFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)

    }

    private fun getCurretUSer() {

        //getString(getString(R.string.key_nameU), null)
        //binding.sigout.setOnClickListener { val p = prefs.edit()p.clear()p.apply() }
        val iolo = viewModel.getUserData(
            getString(R.string.prefs_file),
            getString(R.string.key_nameU),
            getString(R.string.key_email),
            getString(R.string.key_photo_url),
            requireContext()
        )
        Glide.with(requireContext()).load(iolo.photo_url).into(binding.userImg)
        binding.userName.text = iolo.name
        binding.email.text = iolo.email


        binding.sigout.setOnClickListener {
            viewModel.singout(getString(R.string.prefs_file),requireContext() )
            findNavController().navigate(R.id.sigInFragment)
        }


    }


}