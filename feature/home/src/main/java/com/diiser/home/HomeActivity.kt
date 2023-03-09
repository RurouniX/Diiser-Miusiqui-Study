package com.diiser.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diiser.HomeViewModel
import com.diiser.databinding.ActivityHomeBinding
import com.diiser.model.home.DataModel
import com.diiser.navigation.player.PlayerNavigation
import com.diiser.observerEvents
import com.diiser.stateview.StateView
import com.google.android.material.internal.ViewUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding

    private val homeAdapter: HomeViewAdapter by lazy { HomeViewAdapter { clickListHomeItem(it) } }

    private val stateView: StateView by lazy { binding.homeStateview }
    private val listHome: RecyclerView by lazy { binding.list }
    private val searchEditText: EditText by lazy { binding.txtSearch }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupView()
        setupViewModel()

    }

    private fun setupView() {
        with(listHome) {
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            adapter = homeAdapter
        }
        with(searchEditText) {
            setOnEditorActionListener { _, _, _ ->
                homeViewModel.getHomeData(searchEditText.text.toString())
                ViewUtils.hideKeyboard(binding.root)
                true
            }
        }

        with(stateView) {
            setButtonClick {
                homeViewModel.getHomeData()
            }
        }

    }

    private fun setupViewModel() {
        homeViewModel.getHomeData()

        with(homeViewModel) {
            homeDataLiveData.observerEvents(this@HomeActivity, onSuccess = {
                homeAdapter.setItems(it.dataModel)
            }, onError = {
                showErrorView()
            }, onLoading = {
                showLoadingView(it)
                listHome.isVisible = !stateView.isVisible
            })
        }

    }

    private fun clickListHomeItem(dataModel: DataModel) {
        PlayerNavigation.openPlayerActivity(this, dataModel)
    }

    private fun changeListVisibility() {
        listHome.isVisible = !stateView.isVisible

    }

    private fun showErrorView() {
        with(stateView) {
            showErrorState()
            changeListVisibility()
        }
    }

    private fun showLoadingView(visibility: Int) {
        with(stateView) {
            showLoading(visibility)
            changeListVisibility()

        }
    }
}