package com.diiser.player

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.diiser.model.home.DataModel
import com.diiser.navigation.player.PlayerNavigation
import com.diiser.player.IntentExtensions.getParcelable
import com.diiser.player.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private val datamodelExtra by lazy {
        intent.getParcelable(
            PlayerNavigation.EXTRA_DATAMODEL,
            DataModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        datamodelExtra.toString()
    }
}
