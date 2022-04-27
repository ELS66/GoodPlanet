package com.example.goodplanet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.goodplanet.R
import com.example.goodplanet.databinding.ActivityRootMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class RootMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val s = intent.getStringExtra("name");
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home_root, R.id.navigation_map_root, R.id.navigation_me_root)
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bundle = Bundle()
        bundle.putString("name",s)
        navController.setGraph(R.navigation.nav_root,bundle)
        NavigationUI.setupWithNavController(navView, navController)
    }
}