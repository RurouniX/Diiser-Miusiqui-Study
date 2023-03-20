package com.diiser.player

import com.diiser.model.player.OthersMusic
import com.diiser.network.repository.PlayerRepository
import com.diiser.network.utils.ResponseError
import com.diiser.network.utils.handleResultType

class PlayerUseCase(private val playRepository: PlayerRepository) {

    suspend fun getOthersMusicByArtist(
        artistId: Int,
        onSuccess: (OthersMusic) -> Unit,
        onError: (ResponseError) -> Unit
    ) {

        playRepository.getOthersMusicByArtist(artistId).handleResultType(
            success = {
                onSuccess(it)
            },
            error = {
                onError(it)
            }
        )
    }
}