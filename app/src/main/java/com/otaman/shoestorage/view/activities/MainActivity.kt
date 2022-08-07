package com.otaman.shoestorage.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.ActivityMainBinding
import com.otaman.shoestorage.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, MainFragment()).commit()
    }
}