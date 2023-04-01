package com.example.astrop.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.astrop.R
import com.example.astrop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // Agregado
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    @SuppressLint("CutPasteId")
    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.alert_title))
            .setMessage(getString(R.string.alert_message))
            .setIcon(R.drawable.ic_launcher_f)
            .setPositiveButton("Sí") { _, _ ->
                finish()
            }
            .setNegativeButton("No", null)
            .create()

        alertDialog.window?.setLayout(500, 200)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        alertDialog.show()

        // Personaliza el diseño de la alerta de diálogo aquí
        alertDialog.findViewById<TextView>(android.R.id.message)
            ?.setTextColor(ContextCompat.getColor(this, R.color.white))
        alertDialog.findViewById<TextView>(android.R.id.message)?.textSize = 18f
        alertDialog.findViewById<TextView>(android.R.id.message)?.gravity = Gravity.CENTER

    }


}