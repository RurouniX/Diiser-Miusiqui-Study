package com.diiser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diiser.R
import com.diiser.databinding.ActivityHomeBinding
import com.diiser.model.search.Track
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val homeArtistLoading: ProgressBar by lazy { binding.homeArtistLoading }
    private val homeArtistErrorMessage: TextView by lazy { binding.homeArtistErrorMessage }
    private val homeArtistTryAgain: Button by lazy { binding.homeArtistTryAgain }
    private val homeArtistErrorGroup: Group by lazy { binding.homeArtistErrorGroup }
    private val homeArtistList: RecyclerView by lazy { binding.homeArtistList }

    private val homeViewModel: HomeViewModel by viewModel()

    private val trackHomeAdapter: TrackHomeAdapter by lazy { TrackHomeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        if (savedInstanceState == null) {
            homeViewModel.getArtistShuffled(RAMMSTEIN)
        }
        startObservers()

    }

    private fun startObservers() = with(homeViewModel) {
        successLiveData.observe(this@HomeActivity, Observer {
            mountList(it)
        })
        errorLiveData.observe(this@HomeActivity, Observer { showError(it) })
        loadingLiveData.observe(this@HomeActivity, Observer { isLoading(it) })
    }

    private fun isLoading(isLoading: Boolean) {
        homeArtistLoading.visibility = if (isLoading) VISIBLE else GONE
    }

    private fun showError(message: String?) {
        message?.let {
            homeArtistErrorMessage.text = message
            homeArtistTryAgain.setOnClickListener {
                isLoading(true)
                homeArtistErrorGroup.visibility = GONE
                homeViewModel.getArtistShuffled(RAMMSTEIN)
            }
            homeArtistErrorGroup.visibility = VISIBLE

        } ?: run {
            homeArtistErrorGroup.visibility = GONE
        }
    }

    private fun mountList(trackList: List<Track>) = with(homeArtistList) {
        adapter = trackHomeAdapter.apply { setItem(trackList) }
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        visibility = VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shuffle -> trackHomeAdapter.run {
                //                setItem(homeViewModel.reShuffle())

                var intent: Intent? = null
                try {
                    intent = Intent(
                        this@HomeActivity,
                        Class.forName("com.diiser.player.PlayerActivity")
                    )
                    startActivity(intent)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }

                notifyDataSetChanged()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val RAMMSTEIN = "rammstein"

    }
}

