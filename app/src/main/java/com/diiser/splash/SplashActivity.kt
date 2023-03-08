package com.diiser.splash

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.diiser.databinding.ActivitySplashBinding
import com.diiser.navigation.home.HomeNavigation

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashTxt.setOnClickListener {

            HomeNavigation.openHomeActivity(this)


        }


    }

}