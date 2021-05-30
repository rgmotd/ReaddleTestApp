package com.example.readdletestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.readdletestapp.R
import com.example.readdletestapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navHostFragment?.navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    toolbar.title = "List items"
                    toolbar.inflateMenu(R.menu.list_menu)
                }
                R.id.detailsFragment -> {
                    toolbar.title = "Detail item"
                    toolbar.menu.clear()
                }
            }
        }
    }

    val toolbar: MaterialToolbar
        get() = binding.toolbar
}