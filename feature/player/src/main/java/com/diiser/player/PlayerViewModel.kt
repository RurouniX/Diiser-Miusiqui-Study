package com.diiser.player

import android.view.View.VISIBLE
import android.view.View.GONE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diiser.flowstate.FlowState
import com.diiser.flowstate.postLoading
import com.diiser.flowstate.postSuccess
import com.diiser.model.player.OthersMusic
import com.diiser.utils.ProviderContext
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val playerUseCase: PlayerUseCase,
    private val providerContext: ProviderContext
) : ViewModel() {

    private val _musicLiveData = MutableLiveData<FlowState<OthersMusic>>()
    val musicLiveData: LiveData<FlowState<OthersMusic>> = _musicLiveData


    fun getOthersMusicByArtist(artistId: Int) {

        viewModelScope.launch(providerContext.main) {
            _musicLiveData.postLoading(VISIBLE)
            playerUseCase.getOthersMusicByArtist(artistId,
                onSuccess = {
                    _musicLiveData.postLoading(GONE)
                    _musicLiveData.postSuccess(it)
                },
                onError = {
                    _musicLiveData.postLoading(GONE)
                })
        }

    }
}