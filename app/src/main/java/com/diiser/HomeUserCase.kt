package com.diiser

import com.diiser.model.search.Track
import com.diiser.network.repository.HomeRepository
import com.diiser.network.utils.ResultType

class HomeUseCase {

    private val homeRepository: HomeRepository by lazy { HomeRepository() }

    private var lastArtistId = Int.MIN_VALUE
    private var trackList = listOf<Track>()

    suspend fun getShuffledMusic(artistIdList: String, successCall: (List<Track>) -> Unit, errorCall: (Int) -> Unit) {
        homeRepository.getArtists(artistIdList).let { response ->

            when (response) {
                is ResultType.Success -> {
                    response.data.body()?.let {
                        trackList = it.track
//                        successCall.invoke(shuffleMusicList())

                        successCall.invoke(trackList)
                    }
                        ?: run { errorCall.invoke(response.data.code()) }
                }

                is ResultType.Error -> errorCall.invoke(response.data.code())

                is ResultType.Fail -> errorCall.invoke(NETWORK_ERROR_CODE)
            }
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

    companion object{
        const val ARTIST_TYPE = "artist"
        const val NETWORK_ERROR_CODE = 0
    }

}

