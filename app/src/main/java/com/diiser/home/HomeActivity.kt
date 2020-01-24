package com.diiser.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diiser.R
import com.diiser.model.search.Track
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val trackHomeAdapter: TrackHomeAdapter by lazy { TrackHomeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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

