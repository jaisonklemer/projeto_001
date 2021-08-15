package com.klemer.photoexplore

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.klemer.photoexplore.R
import com.klemer.photoexplore.databinding.ActivityMainBinding
import com.klemer.photoexplore.databinding.ActivityMainBinding.*
import com.klemer.photoexplore.extensions.hideKeyboard
import com.klemer.photoexplore.fragments.ImagesFragments

import android.app.SearchManager
import android.content.Context

import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_root, ImagesFragments.newInstance())
                .commit()
        }
    }

    fun getNavView(): NavigationView {
        return navView
    }

    fun getDrawer(): DrawerLayout {
        return drawerLayout
    }

}