package com.otaman.shoestorage.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.ActivityMainBinding
import com.otaman.shoestorage.ui.fragments.mainfragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, MainFragment()).commit()
    }
}