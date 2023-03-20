package com.diiser.player

import com.diiser.model.player.OthersMusic
import com.diiser.network.repository.PlayerRepository
import com.diiser.network.utils.ResponseError
import com.diiser.network.utils.ResultType
import com.diiser.testutils.readJsonFile
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayerUserCaseTest {

    private val playerUseCase: PlayerUseCase by lazy { PlayerUseCase(repository) }

    private val repository = mockk<PlayerRepository>()
    private val searchTerm = 123456

    private val othersMusic =
        javaClass.classLoader?.run { readJsonFile<OthersMusic>(getResourceAsStream("othersMusic.json")) }

    @Test
    fun shouldReturnOthersMusic_whenCall_getOthersMusicByArtist() = runBlocking {
        val expectedResult = ResultType.Success(othersMusic)
        var result: OthersMusic? = null

        coEvery { repository.getOthersMusicByArtist(any()) } coAnswers { expectedResult as ResultType.Success<OthersMusic> }

        playerUseCase.getOthersMusicByArtist(searchTerm, onSuccess = { result = it }, onError = {})

        assert(result?.musicList?.get(0)?.title == "Metal")
    }


    @Test
    fun shouldReturnError_whenCall_getOthersMusicByArtist() = runBlocking {
        val expectedResult = ResultType.Fail(ResponseError(Throwable("Error")))
        var result: ResponseError? = null

        coEvery { repository.getOthersMusicByArtist(any()) } coAnswers { expectedResult }

        playerUseCase.getOthersMusicByArtist(searchTerm, onSuccess = {it.toString()}, onError = {result = it})

        assert(result?.cause?.message == "Error")
    }

}