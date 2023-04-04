package com.example.astrop.ui.mainFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    list: ArrayList<Fragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val listFg = list
    override fun getItemCount(): Int = listFg.size

    override fun createFragment(position: Int): Fragment {
        return  listFg[position]
    }
}