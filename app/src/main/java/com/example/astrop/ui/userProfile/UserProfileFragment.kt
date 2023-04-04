package com.example.astrop.ui.userProfile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.astrop.R
import com.example.astrop.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
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
        val prefs = requireActivity().getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        val phtoUrl = prefs.getString(getString(R.string.key_photo_url), null)
        binding.userName.text = prefs.getString(getString(R.string.key_nameU), null)
        binding.email.text = prefs.getString(getString(R.string.key_email), null)
        Glide.with(requireContext()).load(phtoUrl).into(binding.userImg)

        binding.sigout.setOnClickListener {
            val p = prefs.edit()
            p.clear()
            p.apply()
            findNavController().navigate(R.id.sigInFragment)
        }
    }


}