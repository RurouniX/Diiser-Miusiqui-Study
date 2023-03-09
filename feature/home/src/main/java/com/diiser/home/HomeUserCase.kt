package com.diiser.home

import com.diiser.model.home.SearchModel
import com.diiser.model.search.Track
import com.diiser.network.repository.HomeRepository
import com.diiser.network.utils.ResponseError
import com.diiser.network.utils.handleResultType

class HomeUseCase(private val homeRepository: HomeRepository) {

    private var lastArtistId = Int.MIN_VALUE
    private var trackList = listOf<Track>()

    suspend fun getShuffledMusic(
        artistIdList: String,
        successCall: (List<Track>) -> Unit,
        errorCall: (Int) -> Unit
    ) {
        homeRepository.getArtists(artistIdList).let { response ->

//            when (response) {
//                is ResultType.Success -> {
//                    response.data.body()?.let {
//                        trackList = it.track
////                        successCall.invoke(shuffleMusicList())
//
//                        successCall.invoke(trackList)
//                    }
//                        ?: run { errorCall.invoke(response.data.code()) }
//                }
//
//            }
//        }
        }
    }

    suspend fun getHomeData(
        search: String,
        onSuccess: (SearchModel) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result = homeRepository.getHomeData(search)

        result.handleResultType(
            success = {
                onSuccess(it)
            },
            error = {
                onError(it)
            }
        )
    }
}


//    fun shuffleMusicList(artistList: List<Track> = trackList): List<Artist> {
//
//        if (artistList.isEmpty()) listOf<Artist>()
//
//        val baseShuffledList = artistList.shuffled().toMutableList()
//        val shuffledList = mutableListOf<Artist>()
//        val artistIds = baseShuffledList.filter { it.wrapperType == ARTIST_TYPE }.map { it.id }
//        baseShuffledList.removeAll { artistIds.contains(it.id) && it.wrapperType == ARTIST_TYPE }
//
//        while (baseShuffledList.isNotEmpty()) {
//
//            var tempArtistIds: List<Int>
//
//            do {
//                tempArtistIds = artistIds.shuffled()
//            } while (tempArtistIds.first() == lastArtistId)
//
//            lastArtistId = tempArtistIds.last()
//
//            tempArtistIds.forEach {
//                val tempArtist = baseShuffledList.first { artist -> artist.artistId == it }
//                shuffledList.add(tempArtist)
//                baseShuffledList.remove(tempArtist)
//            }
//
//        }
//
//        return shuffledList
//    }

//companion object {
//    const val ARTIST_TYPE = "artist"
//    const val NETWORK_ERROR_CODE = 0
//}



