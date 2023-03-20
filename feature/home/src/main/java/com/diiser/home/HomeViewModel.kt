package com.diiser.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diiser.flowstate.FlowState
import com.diiser.flowstate.postError
import com.diiser.flowstate.postLoading
import com.diiser.flowstate.postSuccess
import com.diiser.model.home.SearchModel
import com.diiser.model.search.Track
import com.diiser.utils.ProviderContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val homeUseCase: HomeUseCase,
private val providerContext: ProviderContext) :
    ViewModel() {

    private val _homeDataLiveData = MutableLiveData<FlowState<SearchModel>>()
    val homeDataLiveData: LiveData<FlowState<SearchModel>> = _homeDataLiveData

    fun getHomeData(search: String = "rock") {
        _homeDataLiveData.postLoading(View.VISIBLE)
        viewModelScope.launch(providerContext.main) {
            homeUseCase.getHomeData(search, onSuccess = {
                _homeDataLiveData.postLoading(View.GONE)
                _homeDataLiveData.postSuccess(it)
            }, onError = {
                _homeDataLiveData.postLoading(View.GONE)
                _homeDataLiveData.postError(it)
            })
        }
    }
}