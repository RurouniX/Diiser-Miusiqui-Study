package com.diiser.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diiser.HomeViewModel
import com.diiser.R
import com.diiser.databinding.ActivityHomeBinding
import com.diiser.stateview.StateView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding

    private val stateView: StateView by lazy { binding.homeStateview }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        homeViewModel.getHomeData()

    }
}