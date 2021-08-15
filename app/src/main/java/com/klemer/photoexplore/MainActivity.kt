package com.klemer.photoexplore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.klemer.photoexplore.databinding.ActivityMainBinding
import com.klemer.photoexplore.databinding.ActivityMainBinding.*
import com.klemer.photoexplore.fragments.ImagesFragments

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

}