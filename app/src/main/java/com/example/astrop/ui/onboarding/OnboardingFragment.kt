package com.example.astrop.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.astrop.R
import com.example.astrop.databinding.FragmentOnboardingBinding
import com.example.astrop.ui.onboarding.screens.FirstFragment
import com.example.astrop.ui.onboarding.screens.SecondFragment
import com.example.astrop.ui.onboarding.screens.ThirdFragment
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class OnboardingFragment : Fragment() {

    // Se define la variable _binding que almacenará la referencia a la vista del Fragment
    private var _binding: FragmentOnboardingBinding? = null

    // Se define la propiedad binding que retorna la vista almacenada en la variable _binding
    private val binding get() = _binding!!

    // Método onCreateView que infla la vista del Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Se infla la vista del Fragment desde el archivo XML fragment_onboarding.xml
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        // Se define una lista de fragmentos para mostrar en el ViewPager
        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        // Se crea una instancia de la clase ViewPagerAdapter, que es responsable de proporcionar el adaptador de fragmentos para el ViewPager
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager, lifecycle
        )

        // Se encuentra la vista del ViewPager en la jerarquía de vistas y se le asigna el adaptador
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter

        // Se encuentra la vista del DotsIndicator en la jerarquía de vistas y se le asigna el ViewPager para que se actualice cuando se cambia de página
        val indicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)

        // Se retorna la vista inflada del Fragment
        return view
    }
}