package com.diiser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diiser.R
import com.diiser.model.search.Track
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val homeUseCase: HomeUseCase) :
    AndroidViewModel(application) {


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

    //TODO NEW
    fun getHomeData(search: String = "rock") {
        viewModelScope.launch {
            homeUseCase.getHomeData(search, onSuccess = {
                it.toString()
            }, onError = {
                it.toString()
            })
        }
    }

//    private fun getMessageByStatusCode(it: Int) = when (it) {
//        500 -> getApplication<Application>().getString(R.string.generic_error)
//        404 -> getApplication<Application>().getString(R.string.not_found_error)
//        else -> getApplication<Application>().getString(R.string.generic_error)
//    }

}

