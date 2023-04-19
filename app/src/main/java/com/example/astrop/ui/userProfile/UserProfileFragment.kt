package com.example.astrop.ui.userProfile

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
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
        /* SETEA LA INFORMACION DE CUENTA DE USUARIO */
        getCurretUSer()
    }


    private fun setAnimation() {
        val alphaAnimatorImg = ObjectAnimator.ofFloat(binding.imgBg, "alpha", 0.5f, 1f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }
        alphaAnimatorImg.start()
        binding.homeFg.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom)

    }

    private fun getCurretUSer() {
        val iolo = viewModel.getUserData(
            getString(R.string.prefs_file),
            getString(R.string.key_nameU),
            getString(R.string.key_email),
            getString(R.string.key_photo_url),
            requireContext()
        )
        Glide.with(requireContext()).load(iolo.photo_url).into(binding.userImg)
        binding.nameuser.text = getString(R.string.nameuser,iolo.name)
        binding.email.text = getString(R.string.useremail,iolo.email)


        binding.sigout.setOnClickListener {
            viewModel.singout(getString(R.string.prefs_file),requireContext() )
            requireActivity().finish()
        }


    }


}