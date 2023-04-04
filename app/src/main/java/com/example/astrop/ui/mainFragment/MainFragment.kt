package com.example.astrop.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.astrop.databinding.FragmentMainBinding
import com.example.astrop.ui.astroType.AstroTypeFragment
import com.example.astrop.ui.dailyImage.DailyImageFragment
import com.example.astrop.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: MainFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lista = arrayListOf<Fragment>(HomeFragment(), AstroTypeFragment(),DailyImageFragment())
        tabLayout = binding.tabLayout
        viewPager2 = binding.viwPager
        adapter = MainFragmentAdapter(requireActivity().supportFragmentManager, lifecycle, lista)
        tabLayout.addTab(tabLayout.newTab().setText("hola"))
        tabLayout.addTab(tabLayout.newTab().setText("hola tu como tas"))
        tabLayout.addTab(tabLayout.newTab().setText("hola 3"))

        viewPager2.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }


}