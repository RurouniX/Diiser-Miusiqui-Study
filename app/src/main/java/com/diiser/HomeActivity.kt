package com.diiser

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diiser.model.search.Track
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            SavedStateViewModelFactory(application, this)
        )[HomeViewModel::class.java]
    }
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
                notifyDataSetChanged()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val RAMMSTEIN = "rammstein"

    }
}

