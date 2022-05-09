package com.example.medicsapp.home.screen.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivityBottomNavigationBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
class BottomNavigationActivity : BaseActivity() {

    /** Instance variable */
    private lateinit var binding: ActivityBottomNavigationBinding

    /** Overridden Method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController: NavController =
            Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
    }

}