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
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) :
    ViewModel() {


    private val _homeDataLiveData = MutableLiveData<FlowState<SearchModel>>()
    val homeDataLiveData: LiveData<FlowState<SearchModel>> = _homeDataLiveData


    private val _successLiveData by lazy { MutableLiveData<List<Track>>() }
    private val _errorLiveData by lazy { MutableLiveData<String>() }
    private val _loadingLiveData by lazy { MutableLiveData<Boolean>() }

    val successLiveData: LiveData<List<Track>> get() = _successLiveData
    val errorLiveData: LiveData<String?> get() = _errorLiveData
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private lateinit var artistShuffledList: List<Track>

    fun getArtistShuffled(artistIdList: String) {
        _loadingLiveData.postValue(true)

        viewModelScope.launch {
            homeUseCase.getShuffledMusic(artistIdList,
                successCall = { artistList ->
                    artistShuffledList = artistList
                    _successLiveData.value = artistList
                    _loadingLiveData.value = false
                    _errorLiveData.value = null
                },
                errorCall = { code ->
//                    _errorLiveData.value = getMessageByStatusCode(code)
                    _loadingLiveData.value = false
                })
        }

    }

//    fun reShuffle() = homeUseCase.shuffleMusicList()

    fun getHomeData(search: String = "rock") {
        _homeDataLiveData.postLoading(View.VISIBLE)
        viewModelScope.launch {
            homeUseCase.getHomeData(search, onSuccess = {
                _homeDataLiveData.postLoading(View.GONE)
                _homeDataLiveData.postSuccess(it)
            }, onError = {
                _homeDataLiveData.postLoading(View.GONE)
                _homeDataLiveData.postError(it)
            })
        }
    }

//    private fun getMessageByStatusCode(it: Int) = when (it) {
//        500 -> getApplication<Application>().getString(R.string.generic_error)
//        404 -> getApplication<Application>().getString(R.string.not_found_error)
//        else -> getApplication<Application>().getString(R.string.generic_error)
//    }

}