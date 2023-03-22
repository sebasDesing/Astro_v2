package com.example.astrop.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.astrop.R
import com.example.astrop.databinding.FragmentOnboardingBinding
import com.example.astrop.ui.onboarding.screens.FirstFragment
import com.example.astrop.ui.onboarding.screens.SecondFragment
import com.example.astrop.ui.onboarding.screens.ThirdFragment
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager, lifecycle
        )
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter = adapter
        val indicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)
        return view
    }
    private fun onboardingEnd():Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return  sharedPreferences.getBoolean("finished", true)
    }

}