package com.diiser.player

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diiser.flowstate.FlowState
import com.diiser.model.home.SearchModel
import com.diiser.model.player.OthersMusic
import com.diiser.network.utils.ResponseError
import com.diiser.testutils.TestProviderContext
import com.diiser.testutils.readJsonFile
import io.mockk.*
import org.junit.Rule
import org.junit.Test

class PlayerViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val providerContext = TestProviderContext()

    private val othersMusic =
        javaClass.classLoader?.run { readJsonFile<OthersMusic>(getResourceAsStream("othersMusic.json")) }

    private val viewModel: PlayerViewModel by lazy { PlayerViewModel(useCase, providerContext) }
    private val useCase = mockk<PlayerUseCase>()

    @Test
    fun shouldReturnMusicList_whenSuccess() {

        coEvery {
            useCase.getOthersMusicByArtist(
                123456,
                onSuccess = captureLambda(),
                onError = any()
            )
        } coAnswers {
            lambda<(OthersMusic) -> Unit>().invoke(othersMusic!!)
        }

        viewModel.getOthersMusicByArtist(123456)
        assert(viewModel.musicLiveData.value?.status == FlowState.Status.SUCCESS)
        viewModel.musicLiveData.value?.data?.musicList?.let{
            assert(it.size == 1)
            assert(it[0].title == "Metal")
            assert(it[0].duration == 80)
            assert(it[0].link == "https://www.deezer.com/track/1741590727")
        }
    }

    @Test
    fun shouldReturnErrorState_whenCallHomeDataFunction() {

        coEvery {
            useCase.getOthersMusicByArtist(
                123456,
                onSuccess = any(),
                onError = captureLambda()
            )
        } coAnswers {
            lambda<(ResponseError) -> Unit>().invoke(ResponseError(Throwable()))
        }

        viewModel.getOthersMusicByArtist(123456)
        assert(viewModel.musicLiveData.value?.status == FlowState.Status.ERROR)
    }

    @Test
    fun shouldReturnLoadingState_whenCallHomeDataFunction() {

        coEvery {
            useCase.getOthersMusicByArtist(
                123456,
                onSuccess = any(),
                onError = any()
            )
        } just Runs

        viewModel.getOthersMusicByArtist(123456)
        assert(viewModel.musicLiveData.value?.status == FlowState.Status.LOADING)
    }

}